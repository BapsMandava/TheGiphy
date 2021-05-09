package com.example.thegiphyapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.thegiphyapp.model.GifDataBase
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.model.MyFavoriteGifDao

class MyFavoritiesRepository(private val application:Application) {

    private var myFavoriteGifDao: MyFavoriteGifDao
    init {
        myFavoriteGifDao = GifDataBase.getDatabase(application).MyFavoriteGifDao()
    }

    suspend fun readAllData(): List<GiphyData> {
        return myFavoriteGifDao.readAllData()
    }
    suspend fun addFavoriteGif(myFavoritesGif:GiphyData) {
        myFavoriteGifDao.addFavoriteGif(myFavoritesGif)
    }
}