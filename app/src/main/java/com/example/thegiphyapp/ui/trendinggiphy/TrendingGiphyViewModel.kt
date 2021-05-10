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

class TrendingGiphyViewModel(private val giphyServiceRepository: GiphyServiceRepository,private val myFavoritiesRepository: MyFavoritiesRepository) : ViewModel() {

    private val query = MutableLiveData<String>()

    private val _readAllData: MutableLiveData<List<String>> =  MutableLiveData()
    val readAllData : LiveData<List<String>>
        get() = _readAllData

    init {
       fetchAllRecords()
    }

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            GiphyPaging(query, giphyServiceRepository)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String) {
        query.postValue(s)
    }

    fun addMyFavorities(myFavoritesGif:GiphyData){
        viewModelScope.launch(Dispatchers.IO) {
             if(myFavoritiesRepository.addFavoriteGif(myFavoritesGif)>0) {
                 fetchAllRecords()
             }
        }
    }

    fun deleteMyFavorities(myFavoritesGif:GiphyData){
        viewModelScope.launch(Dispatchers.IO) {
            myFavoritiesRepository.deleteFavGif(myFavoritesGif)
            fetchAllRecords()
        }
    }

    fun fetchAllRecords(){
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(myFavoritiesRepository.readAllData())
        }
    }
}