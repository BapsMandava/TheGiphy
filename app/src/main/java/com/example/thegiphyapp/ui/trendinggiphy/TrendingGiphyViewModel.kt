package com.example.thegiphyapp.ui.trendinggiphy

import android.util.Log
import android.util.Log.VERBOSE
import androidx.lifecycle.*
import androidx.paging.*
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.repository.GiphyServiceRepository
import com.example.thegiphyapp.repository.MyFavoritiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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