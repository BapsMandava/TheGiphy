package com.example.thegiphyapp.ui.trendinggiphy

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegiphyapp.R
import com.example.thegiphyapp.databinding.FragmentTrendinggiphyBinding
import com.example.thegiphyapp.model.GiphyData
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrendingGiphyFragment : Fragment() {
    lateinit var binding: FragmentTrendinggiphyBinding
    val trendingGiphyViewModel: TrendingGiphyViewModel by viewModel<TrendingGiphyViewModel>()
    val trendingGiphyPagingAdapter = TrendingGiphyPagingAdapter({ item -> doClick(item) })

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
        setRecyclerView()
        trendingGiphyViewModel.setQuery("")
        trendingGiphyViewModel.list.observe(viewLifecycleOwner) {
            trendingGiphyPagingAdapter.submitData(lifecycle, it)
        }
    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = trendingGiphyPagingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
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
        trendingGiphyViewModel.addMyFavorities(myFavoritesGif)
    }

}