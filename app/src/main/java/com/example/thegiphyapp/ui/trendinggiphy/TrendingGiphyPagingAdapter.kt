package com.example.thegiphyapp.ui.trendinggiphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thegiphyapp.BR
import com.example.thegiphyapp.databinding.ViewHolderGiphyBinding
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.utils.Transform.isFavGif

class TrendingGiphyPagingAdapter internal constructor( val adapterOnClick : (GiphyData) -> Unit) : PagingDataAdapter<GiphyData,TrendingGiphyPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<GiphyData>(){
            override fun areItemsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GiphyData, newItem: GiphyData): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderGiphyBinding):RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var giphyData = getItem(position)
        holder.viewDataBinding.setVariable(BR.giphyData,giphyData)
        holder.viewDataBinding.likeIcon.isChecked = isFavGif(giphyData)
    /*    holder.viewDataBinding.gifImageCard.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade)*/
        holder.viewDataBinding.likeIcon.setOnCheckedChangeListener(
            { buttonView, isChecked ->
                if(buttonView.isPressed) {
                    giphyData?.isFavorite = isChecked
                    giphyData?.let {
                        adapterOnClick(it)
                    }
                }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding = ViewHolderGiphyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

}