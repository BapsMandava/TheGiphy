package com.example.thegiphyapp.utils

import com.example.thegiphyapp.data.TrendingGiphyData
import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.model.GiphyData
import retrofit2.Response

object Transform {

    fun convertGifResponsetoModel(trendingGiphyDataList: List<TrendingGiphyData>?): List<GiphyData> {
        var giphyDataList = mutableListOf<GiphyData>()
        trendingGiphyDataList?.forEach {
            giphyDataList.add(GiphyData(id = it.id,title = it.title,gif_url = it.images.original.url,isFavorite = false))
        }
        return giphyDataList
    }
}