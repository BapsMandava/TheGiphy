package com.example.thegiphyapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide


@BindingAdapter("loadGif")
fun loadGif(view:ImageView,url:String){
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    Glide.with(view).asGif().load(url).placeholder(circularProgressDrawable).into(view)
}