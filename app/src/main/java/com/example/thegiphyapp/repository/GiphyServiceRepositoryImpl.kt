package com.example.thegiphyapp.repository

import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.network.ServiceInterface
import retrofit2.Response

class GiphyServiceRepositoryImpl (private val serviceInterface: ServiceInterface): GiphyServiceRepository {

    override suspend fun giphyTrendingList(offSet:String): Response<TrendingGiphyResponse> {
        return serviceInterface.fetchTreandingGiphyList(offSet = offSet)
    }
}