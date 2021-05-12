package com.example.thegiphyapp.ui.trendinggiphy

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.repository.GiphyServiceRepository
import java.lang.Exception

class GiphyPaging(val searchValue: String, val giphyServiceRepo: GiphyServiceRepository) :
    PagingSource<Int, GiphyData>() {
    override fun getRefreshKey(state: PagingState<Int, GiphyData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyData> {
        val page = params.key ?: 0
        return try {
            if (searchValue.isNullOrEmpty()) {
                val giphyTrendingList = giphyServiceRepo.giphyTrendingList(offSet = page.toString())
                LoadResult.Page(
                    data = giphyTrendingList,
                    prevKey = if (page == 0) null else page - 10,
                    nextKey = if (giphyTrendingList.isEmpty()) null else page + 10
                )
            } else {
                val searchGiphyData = giphyServiceRepo.searchGiphy(
                    offSet = page.toString(),
                    searchValue = searchValue
                )

                LoadResult.Page(
                    data = searchGiphyData,
                    prevKey = if (page == 0) null else page - 10,
                    nextKey = if (searchGiphyData.isEmpty()!!) null else page + 10
                )

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}