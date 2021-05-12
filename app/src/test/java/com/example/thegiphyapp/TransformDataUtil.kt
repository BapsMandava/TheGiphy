package com.example.thegiphyapp

import com.example.thegiphyapp.data.TrendingGiphyResponse
import com.example.thegiphyapp.model.GiphyData
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

    @Test
    fun `test isFavGif test is true`(){
        val trendingGiphyResponse = getTrendingGiphyList()
        val giphyListData = Transform.convertGifResponsetoModel(trendingGiphyResponse.data)
        Application.allFavGifData = giphyListData
        Assert.assertTrue(Transform.isFavGif(giphyListData.get(0)))
        Assert.assertTrue(Transform.isFavGif(giphyListData.get(2)))
    }

    @Test
    fun `test isFavGif test is false`(){
        val trendingGiphyResponse = getTrendingGiphyList()
        val giphyListData = Transform.convertGifResponsetoModel(trendingGiphyResponse.data)
        Application.allFavGifData = giphyListData
        val giphyData1 = GiphyData("Y68ojNabIQSdLB1PPP", "Brits GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1PPP/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        val giphyData2 = GiphyData("Y68ojNabIQSdLB1QQQ", "GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1QQQ/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        Assert.assertFalse(Transform.isFavGif(giphyData1))
        Assert.assertFalse(Transform.isFavGif(giphyData2))
    }

    private fun getTrendingGiphyList():TrendingGiphyResponse{
        val path = "../app/src/main/assets/mocks/trendinggiphy.json"
        val bufferReader = BufferedReader(FileReader(path) as Reader?)
        return Gson().fromJson(bufferReader, TrendingGiphyResponse::class.java)
    }

}