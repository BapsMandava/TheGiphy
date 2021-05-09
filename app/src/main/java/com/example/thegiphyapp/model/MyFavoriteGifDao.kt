package com.example.thegiphyapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MyFavoriteGifDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteGif(giphyData:GiphyData)

    @Query("SELECT * FROM myfavoritesgif_table")
    suspend fun readAllData(): List<GiphyData>

}