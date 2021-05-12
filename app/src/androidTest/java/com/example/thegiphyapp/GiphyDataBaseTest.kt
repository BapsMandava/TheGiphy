package com.example.thegiphyapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.thegiphyapp.model.GifDataBase
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.model.MyFavoriteGifDao
import com.example.thegiphyapp.utils.Transform
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class GiphyDataBaseTest : TestCase() {

    private lateinit var myFavoriteGifDao: MyFavoriteGifDao
    private lateinit var db: GifDataBase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, GifDataBase::class.java
        ).build()
        myFavoriteGifDao = db.MyFavoriteGifDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertandfetchGiphyData() = runBlocking {
        val giphyData = GiphyData("Y68ojNabIQSdLB1FXYfdsfs", "Excited Brits GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1FXY/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        myFavoriteGifDao.addFavoriteGif(giphyData)
        val giphyDataFromDB = myFavoriteGifDao.readAllData()
        Assert.assertEquals(giphyDataFromDB.get(0).gif_url,giphyData.gif_url)
    }

    @Test
    fun deleteGiphyData() = runBlocking {
        val giphyData1 = GiphyData("Y68ojNabIQSdLB1FXY", "Excited Brits GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1FXY/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        val giphyData2 = GiphyData("Y68ojNabIQSdLB1PPP", "Excited Brits GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1FXY/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        myFavoriteGifDao.addFavoriteGif(giphyData1)
        myFavoriteGifDao.addFavoriteGif(giphyData2)
        val giphyDataFromDB = myFavoriteGifDao.readAllData()
        Assert.assertEquals(giphyDataFromDB.get(0).gif_url,giphyData1.gif_url)
        val value = myFavoriteGifDao.deleteFavGiphy(giphyData1)
        Assert.assertEquals(value,1)
        val giphyDataFromDBDeletedData = myFavoriteGifDao.readAllData()
        Assert.assertNotEquals(giphyDataFromDBDeletedData.get(0).id,giphyData1.id)
    }

    @Test
    fun containsFavGiphyTest() = runBlocking {
        val giphyData1 = GiphyData("Y68ojNabIQSdLB1FXY", "Excited Brits GIFs", "https://media1.giphy.com/media/Y68ojNabIQSdLB1FXY/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        val giphyData2 = GiphyData("Y68ojNabIQSdLB1PPP", "Brits GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1PPP/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")
        val giphyData3 = GiphyData("Y68ojNabIQSdLB1QQQ", "GIF by BRIT Awards", "https://media1.giphy.com/media/Y68ojNabIQSdLB1QQQ/giphy.gif?cid=62923bd97fohex5n419l52c063ids8il4m8j3zku8aw801k0&rid=giphy.gif&ct=g")

        myFavoriteGifDao.addFavoriteGif(giphyData1)
        myFavoriteGifDao.addFavoriteGif(giphyData2)
        myFavoriteGifDao.addFavoriteGif(giphyData3)
        Application.allFavGifData = myFavoriteGifDao.readAllData()
        Assert.assertTrue(Transform.isFavGif(giphyData1))
        Assert.assertTrue(Transform.isFavGif(giphyData2))

        val value = myFavoriteGifDao.deleteFavGiphy(giphyData1)
        Assert.assertEquals(value,1)
        Application.allFavGifData = myFavoriteGifDao.readAllData()
        Assert.assertFalse(Transform.isFavGif(giphyData1))

    }
}