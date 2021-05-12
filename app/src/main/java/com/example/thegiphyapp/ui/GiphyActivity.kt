package com.example.thegiphyapp.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.thegiphyapp.BaseActivity
import com.example.thegiphyapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class GiphyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter=GiphyPagerAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter=adapter
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        TabLayoutMediator(tabLayout, viewPager2){ tab, position->
            when(position){
                0 -> {
                    tab.text = this.getText(R.string.title_trendinggiphy)
                    tab.icon = this.getDrawable(R.drawable.ic_baseline_trending_up)
                }
                1 -> {
                    tab.text = this.getText(R.string.title_favorites)
                    tab.icon = this.getDrawable(R.drawable.ic_baseline_favorite)
                }
            }
        }.attach()

    }
}