package com.example.thegiphyapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "myfavoritesgif_table")
data class GiphyData(
    @PrimaryKey
    @NotNull
    var id: String = "",
    var title: String? = "",
    var gif_url: String? = "",
    var isFavorite: Boolean = false
)