package com.example.thegiphyapp.repository

import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.model.GiphyData
import retrofit2.Response

interface GiphyServiceRepository {
    suspend fun giphyTrendingList(offSet:String): List<GiphyData>
    suspend fun searchGiphy(offSet:String,searchValue:String): List<GiphyData>
}