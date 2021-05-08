package com.example.thegiphyapp.repository

import com.example.thegiphyapp.data.TrendingGiphyResponse
import retrofit2.Response

interface GiphyServiceRepository {
    suspend fun giphyTrendingList(offSet:String): Response<TrendingGiphyResponse>
}