package com.example.thegiphyapp.data

data class TrendingGiphyResponse(
    val data: List<TrendingGiphyData>,
    val meta: Meta,
    val pagination: Pagination
)