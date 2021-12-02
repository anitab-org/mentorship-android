package org.anitab.mentorship.remote.datamanager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.services.UserService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class UserPagingSource(private val service: UserService) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val pageIndex = params.key ?: STARTING_PAGE_INDEX
            val response: List<User> = service.getVerifiedUsers()

            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = pageIndex + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(IOException("Please check internet connection"))
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}