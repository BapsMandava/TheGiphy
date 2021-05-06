package com.example.thegiphyapp.ui.trendinggiphy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.thegiphyapp.R

class TrendingGiphyFragment : Fragment() {

    private lateinit var trendingGiphyViewModel: TrendingGiphyViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        trendingGiphyViewModel =
                ViewModelProvider(this).get(TrendingGiphyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trendinggiphy, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        trendingGiphyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}