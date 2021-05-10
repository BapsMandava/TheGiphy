package com.example.thegiphyapp.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MyFavoriteGifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteGif(giphyData:GiphyData) : Long

    @Query("SELECT id FROM myfavoritesgif_table")
    suspend fun readAllData(): List<String>

    @Delete
    suspend fun deleteFavGiphy(giphyData:GiphyData) : Int

}