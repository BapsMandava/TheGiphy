package com.example.thegiphyapp.utils

import com.example.thegiphyapp.Application
import com.example.thegiphyapp.data.TrendingGiphyData
import com.example.thegiphyapp.model.GiphyData

object Transform {

    fun convertGifResponsetoModel(trendingGiphyDataList: List<TrendingGiphyData>?): List<GiphyData> {
        var giphyDataList = mutableListOf<GiphyData>()
        trendingGiphyDataList?.forEach {
            giphyDataList.add(GiphyData(id = it.id,title = it.title,gif_url = it.images.original.url,isFavorite = false))
        }
        return giphyDataList
    }

     fun isFavGif(giphyData:GiphyData?):Boolean {
        return Application.allFavGifData?.any{
            it.id.equals(giphyData?.id)
        } ?: false
    }
}