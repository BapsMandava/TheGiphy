package com.example.thegiphyapp.ui.trendinggiphy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thegiphyapp.databinding.FragmentTrendinggiphyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingGiphyFragment : Fragment() {
    lateinit var binding: FragmentTrendinggiphyBinding
    val trendingGiphyViewModel: TrendingGiphyViewModel by viewModel<TrendingGiphyViewModel>()
    val trendingGiphyPagingAdapter = TrendingGiphyPagingAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendinggiphyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()


        binding.giphySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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


        trendingGiphyViewModel.list.observe(viewLifecycleOwner) {
            trendingGiphyPagingAdapter.submitData(lifecycle, it)
        }
    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = trendingGiphyPagingAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

}