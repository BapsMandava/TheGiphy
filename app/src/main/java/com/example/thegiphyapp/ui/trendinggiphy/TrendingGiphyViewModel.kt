package com.example.thegiphyapp.ui.trendinggiphy

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.thegiphyapp.data.pagingdata.GiphyPaging
import com.example.thegiphyapp.network.ServiceInterface
import com.example.thegiphyapp.repository.GiphyServiceRepository
import com.example.thegiphyapp.repository.GiphyServiceRepositoryImpl

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