package com.example.thegiphyapp.ui

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thegiphyapp.ui.myfavorites.MyFavoritesFragment
import com.example.thegiphyapp.ui.trendinggiphy.TrendingGiphyFragment

class GiphyPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                TrendingGiphyFragment()
            }
            1->{
                MyFavoritesFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}