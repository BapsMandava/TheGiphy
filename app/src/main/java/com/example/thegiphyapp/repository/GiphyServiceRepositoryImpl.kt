package com.example.thegiphyapp.repository

import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.network.ServiceInterface
import com.example.thegiphyapp.utils.Transform


class GiphyServiceRepositoryImpl (private val serviceInterface: ServiceInterface): GiphyServiceRepository {

    override suspend fun giphyTrendingList(offSet:String): List<GiphyData> {
        var response = serviceInterface.fetchTreandingGiphyList(offSet = offSet)
        return Transform.convertGifResponsetoModel(response.body()?.data)
    }

    override suspend fun searchGiphy(offSet: String, searchValue: String): List<GiphyData> {
        var response = serviceInterface.searchTreandingGiphyList(offSet = offSet, q=searchValue)
        return Transform.convertGifResponsetoModel(response.body()?.data)
    }
}