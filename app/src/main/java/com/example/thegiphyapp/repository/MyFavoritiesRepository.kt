package com.example.thegiphyapp.repository

import android.app.Application
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
    suspend fun addFavoriteGif(myFavoritesGif:GiphyData) : Long {
       return  myFavoriteGifDao.addFavoriteGif(myFavoritesGif)
    }

    suspend fun deleteFavGif(myFavoritesGif:GiphyData): Int {
        return myFavoriteGifDao.deleteFavGiphy(myFavoritesGif)
    }
}