package com.example.thegiphyapp.repository

import com.example.thegiphyapp.model.GiphyData

interface GiphyServiceRepository {
    suspend fun giphyTrendingList(offSet:String): List<GiphyData>
    suspend fun searchGiphy(offSet:String,searchValue:String): List<GiphyData>
}