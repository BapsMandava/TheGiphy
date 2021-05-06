package com.example.thegiphyapp.ui.trendinggiphy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrendingGiphyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TrendingGiphy"
    }

    val text: LiveData<String> = _text
}