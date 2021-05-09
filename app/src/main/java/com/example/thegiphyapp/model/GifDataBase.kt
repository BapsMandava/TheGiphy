package com.example.thegiphyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GiphyData::class],version = 1, exportSchema = false)
abstract class GifDataBase: RoomDatabase() {

    abstract fun MyFavoriteGifDao(): MyFavoriteGifDao

    companion object {
        @Volatile
        private var INSTANCE: GifDataBase? = null

        fun getDatabase(context: Context): GifDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GifDataBase::class.java,
                    "gif_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}