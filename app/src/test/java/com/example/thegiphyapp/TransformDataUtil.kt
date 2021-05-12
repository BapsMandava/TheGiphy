package com.example.thegiphyapp

import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.utils.Transform
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader
import java.io.Reader

class TransformDataUtil {

    @Test
    fun `test trending list`(){
        val trendingGiphyResponse = getTrendingGiphyList()
        Assert.assertEquals(10,trendingGiphyResponse.data.size)
    }

    @Test
    fun `test conver tGifResponse to Model`(){
        val trendingGiphyResponse = getTrendingGiphyList()
        val giphyListData = Transform.convertGifResponsetoModel(trendingGiphyResponse.data)
        Assert.assertEquals(giphyListData.size,trendingGiphyResponse.data.size)
        Assert.assertEquals(giphyListData.get(0).gif_url,trendingGiphyResponse.data.get(0).images.original.url)
    }

    private fun getTrendingGiphyList():TrendingGiphyResponse{
        val path = "../app/src/main/assets/mocks/trendinggiphy.json"
        val bufferReader = BufferedReader(FileReader(path) as Reader?)
        return Gson().fromJson(bufferReader, TrendingGiphyResponse::class.java)
    }


}