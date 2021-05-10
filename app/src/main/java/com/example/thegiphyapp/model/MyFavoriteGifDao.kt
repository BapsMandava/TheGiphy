package com.example.thegiphyapp.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MyFavoriteGifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteGif(giphyData:GiphyData) : Long

    @Query("SELECT * FROM myfavoritesgif_table")
    suspend fun readAllData(): List<GiphyData>

    @Delete
    suspend fun deleteFavGiphy(giphyData:GiphyData) : Int

}