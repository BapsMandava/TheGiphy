package com.example.thegiphyapp.ui.trendinggiphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thegiphyapp.BR
import com.example.thegiphyapp.data.TrendingGiphyData
import com.example.thegiphyapp.databinding.ViewHolderGiphyBinding

class TrendingGiphyPagingAdapter : PagingDataAdapter<TrendingGiphyData,TrendingGiphyPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<TrendingGiphyData>(){
            override fun areItemsTheSame(oldItem: TrendingGiphyData, newItem: TrendingGiphyData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TrendingGiphyData, newItem: TrendingGiphyData): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MyViewHolder(val viewDataBinding: ViewHolderGiphyBinding):RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.giphyData,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val binding = ViewHolderGiphyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
}