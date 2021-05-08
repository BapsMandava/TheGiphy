package com.example.thegiphyapp.network

import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {
    /**
     * get treanding giphy list from the service
     */
    @GET("trending?")
    suspend fun fetchTreandingGiphyList(
            @Query("api_key") api_key: String = Constants.api_key,
            @Query("limit") limit: String = "10",
            @Query("rating") rating: String = Constants.rating,
            @Query("offSet") offSet: String,
    ): Response<TrendingGiphyResponse>
}