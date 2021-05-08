package com.example.thegiphyapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadGif(view:ImageView,url:String){
    Glide.with(view).load(url).into(view)
}