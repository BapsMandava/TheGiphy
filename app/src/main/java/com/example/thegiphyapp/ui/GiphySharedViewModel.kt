package com.example.thegiphyapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.repository.MyFavoritiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GiphySharedViewModel(private val myFavoritiesRepository: MyFavoritiesRepository) : ViewModel() {

    private val _readAllData: MutableLiveData<List<GiphyData>> =  MutableLiveData()
    val readAllData : LiveData<List<GiphyData>>
        get() = _readAllData


    init {
        fetchAllRecords()
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