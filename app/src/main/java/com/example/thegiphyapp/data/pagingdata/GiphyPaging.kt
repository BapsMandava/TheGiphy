package com.example.thegiphyapp.data.pagingdata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.thegiphyapp.data.TrendingGiphyData
import com.example.thegiphyapp.repository.GiphyServiceRepository
import com.example.thegiphyapp.repository.GiphyServiceRepositoryImpl
import java.lang.Exception

class GiphyPaging(val s:String,val giphyServiceRepo: GiphyServiceRepository) : PagingSource<Int,TrendingGiphyData>() {
    override fun getRefreshKey(state: PagingState<Int, TrendingGiphyData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingGiphyData> {
        val page = params.key ?: 10
        return try {
            val data = giphyServiceRepo.giphyTrendingList(offSet = page.toString())
            LoadResult.Page(
                data = data.body()?.data!!,
                prevKey = if(page==1) null else page-10,
                nextKey = if(data.body()?.data?.isEmpty()!!) null else page+10
            )
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}