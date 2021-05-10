package com.example.thegiphyapp.ui.trendinggiphy

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegiphyapp.Application
import com.example.thegiphyapp.Application.Companion.allFavGifData
import com.example.thegiphyapp.R
import com.example.thegiphyapp.databinding.FragmentTrendinggiphyBinding
import com.example.thegiphyapp.model.GiphyData
import com.example.thegiphyapp.ui.GiphyActivity
import com.example.thegiphyapp.ui.GiphySharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrendingGiphyFragment : Fragment() {
    lateinit var binding: FragmentTrendinggiphyBinding
    val trendingGiphyViewModel: TrendingGiphyViewModel by viewModel<TrendingGiphyViewModel>()
    val sharedGiphyViewModel: GiphySharedViewModel by sharedViewModel<GiphySharedViewModel>()
    val trendingGiphyPagingAdapter = TrendingGiphyPagingAdapter({ item -> doClick(item) })
    private lateinit var mActivity: GiphyActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendinggiphyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as GiphyActivity
        setRecyclerView()
        setTofetchAllFavGifRecords()
        trendingGiphyViewModel.setQuery("")
        trendingGiphyViewModel.list.observe(viewLifecycleOwner) {
            trendingGiphyPagingAdapter.submitData(lifecycle, it)
        }
    }

    private fun setRecyclerView() {
        binding.giphyRecycler.apply {
            adapter = trendingGiphyPagingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setTofetchAllFavGifRecords(){
        sharedGiphyViewModel.readAllData.observe(mActivity, Observer {
            Application.allFavGifData = it
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu?.findItem(R.id.action_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    trendingGiphyViewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
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