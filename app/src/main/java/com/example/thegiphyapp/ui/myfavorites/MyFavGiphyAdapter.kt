package com.example.thegiphyapp.ui.myfavorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thegiphyapp.BR
import com.example.thegiphyapp.databinding.ViewHolderGiphyBinding
import com.example.thegiphyapp.model.GiphyData

class MyFavGiphyAdapter internal constructor(val adapterOnClick : (GiphyData) -> Unit) : RecyclerView.Adapter<MyFavGiphyAdapter.MyViewHolder>() {

    private var myFavGiphyList = emptyList<GiphyData>()

    override fun getItemCount() = myFavGiphyList.size

    inner class MyViewHolder(val viewDataBinding: ViewHolderGiphyBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var giphyData = myFavGiphyList.get(position)
        holder.viewDataBinding.setVariable(BR.giphyData, giphyData)
        holder.viewDataBinding.likeIcon.isChecked = giphyData.isFavorite
        holder.viewDataBinding.likeIcon.setOnCheckedChangeListener(
            { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    giphyData?.isFavorite = isChecked
                    giphyData?.let {
                        adapterOnClick(it)
                    }
                }
            })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun setfavGiphys(myFavGiphyList: List<GiphyData>) {
        this.myFavGiphyList = emptyList()
        this.myFavGiphyList = myFavGiphyList
        notifyDataSetChanged()
    }
}