package com.example.thegiphyapp

import android.app.Application
import com.example.thegiphyapp.di.appModule
import com.example.thegiphyapp.model.GiphyData
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Application context
 */
class Application: Application(){

    companion object {
        var allFavGifData : List<GiphyData>? = null
    }
    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(appModule)
        }
    }
}