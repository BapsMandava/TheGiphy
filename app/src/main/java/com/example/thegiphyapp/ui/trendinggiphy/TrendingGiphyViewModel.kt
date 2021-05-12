package com.example.thegiphyapp.ui.trendinggiphy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.thegiphyapp.repository.GiphyServiceRepository

class TrendingGiphyViewModel(private val giphyServiceRepository: GiphyServiceRepository) : ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            GiphyPaging(query, giphyServiceRepository)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }
}