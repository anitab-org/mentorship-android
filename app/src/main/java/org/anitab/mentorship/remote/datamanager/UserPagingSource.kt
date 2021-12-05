package org.anitab.mentorship.remote.datamanager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.anitab.mentorship.models.User
import org.anitab.mentorship.remote.services.UserService
import org.anitab.mentorship.utils.Constants.START_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val service: UserService) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: START_PAGE_INDEX
        return try {
            val response: List<User> = service.getVerifiedUsers(pageIndex, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == START_PAGE_INDEX) null else pageIndex - 1,
                nextKey = if (response.isEmpty()) null else pageIndex + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}