package com.example.thegiphyapp.di

import com.example.thegiphyapp.network.GiphyServiceApiHelper
import com.example.thegiphyapp.network.ServiceInterface
import com.example.thegiphyapp.repository.GiphyServiceRepository
import com.example.thegiphyapp.repository.GiphyServiceRepositoryImpl
import com.example.thegiphyapp.repository.MyFavoritiesRepository
import com.example.thegiphyapp.ui.GiphySharedViewModel
import com.example.thegiphyapp.ui.trendinggiphy.TrendingGiphyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    single { getGiphyService() }
    factory { GiphyServiceRepositoryImpl(get()) as GiphyServiceRepository }
    factory { MyFavoritiesRepository(get()) as MyFavoritiesRepository }
    viewModel { TrendingGiphyViewModel(get()) }
    viewModel { GiphySharedViewModel(get()) }
}


fun getGiphyService(): ServiceInterface? {
    return GiphyServiceApiHelper().getServiceinterface()
}