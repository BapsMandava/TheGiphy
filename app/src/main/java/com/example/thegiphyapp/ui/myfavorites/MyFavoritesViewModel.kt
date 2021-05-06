package com.example.thegiphyapp.ui.myfavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyFavoritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "MyFavorites"
    }
    val text: LiveData<String> = _text
}