package com.example.thegiphyapp.bindingadapters

import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.thegiphyapp.R

@BindingAdapter("loadGif")
fun loadGif(view:ImageView,url:String){
    Glide.with(view).asGif().load(url).into(view)
}