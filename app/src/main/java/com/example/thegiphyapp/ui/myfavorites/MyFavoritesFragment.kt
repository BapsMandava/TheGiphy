package com.example.thegiphyapp.ui.myfavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegiphyapp.databinding.FragmentMyfavoritesBinding
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.ui.GiphyActivity
import com.example.thegiphyapp.ui.GiphySharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyFavoritesFragment : Fragment() {
    lateinit var binding: FragmentMyfavoritesBinding
    val sharedGiphyViewModel: GiphySharedViewModel by sharedViewModel<GiphySharedViewModel>()
    private lateinit var mActivity: GiphyActivity
    val myFavGiphyAdapter = MyFavGiphyAdapter({ item -> doClick(item) })


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyfavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as GiphyActivity
        setRecyclerView()
        setTofetchAllFavGifRecords()
    }

    private fun setRecyclerView() {
        binding.myFavGiphyRecycler.apply {
            adapter = myFavGiphyAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }

    private fun setTofetchAllFavGifRecords(){
        sharedGiphyViewModel.readAllData.observe(mActivity, Observer {
            myFavGiphyAdapter.clear()
            myFavGiphyAdapter.setRepos(it)
        })
    }

    fun doClick(myFavoritesGif: GiphyData){
        if(myFavoritesGif.isFavorite){
            sharedGiphyViewModel.addMyFavorities(myFavoritesGif)
        } else {
            sharedGiphyViewModel.deleteMyFavorities(myFavoritesGif)
        }
    }

}