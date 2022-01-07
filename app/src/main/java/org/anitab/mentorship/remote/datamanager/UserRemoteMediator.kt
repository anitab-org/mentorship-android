package org.anitab.mentorship.remote.datamanager

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import java.io.IOException
import org.anitab.mentorship.local.RemoteKeysEntity
import org.anitab.mentorship.local.UserDatabase
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.services.UserService
import org.anitab.mentorship.utils.Constants.START_PAGE_INDEX
import retrofit2.HttpException

@ExperimentalPagingApi
class UserRemoteMediator(
    private val userService: UserService,
    private val userDatabase: UserDatabase
) : RemoteMediator<Int, User>() {

    override suspend fun initialize(): InitializeAction {
        // Launching paging with refresh first, without triggering prepend or append.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {
        // calculating page number as per load type.
        val page = when (loadType) {
            LoadType.APPEND -> {
                val remoteKey = userDatabase.remoteKeyDao().getRemoteKeys().lastOrNull()
                val nextKey = remoteKey?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKey = userDatabase.remoteKeyDao().getRemoteKeys().firstOrNull()
                val prevKey = remoteKey?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                prevKey
            }
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: START_PAGE_INDEX
            }
        }

        try {
            // making network call
            val users: List<User> = userService.getVerifiedUsers(page, state.config.pageSize)

            // storing if user list is empty
            val endOfPaginationReached = users.isEmpty()

            userDatabase.withTransaction {
                // clear all table in database
                if (loadType == LoadType.REFRESH) {
                    userDatabase.remoteKeyDao().clearRemoteKeys()
                    userDatabase.userDao().clearAllUsers()
                }

                // storing previous and next keys
                val prevKey = if (page == START_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                // storing the remote key and fetched users
                val keys = users.map {
                    RemoteKeysEntity(userId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                userDatabase.remoteKeyDao().insertAll(keys)
                userDatabase.userDao().insertUsers(users)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, User>): RemoteKeysEntity? {
        // Getting the remote key closest to anchor position
        // Paging library will load data after the anchor position
        // and get that item, which is closest to anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { userId ->
                userDatabase.remoteKeyDao().remoteKeysRepoId(userId)
            }
        }
    }
}
