package com.example.thegiphyapp.ui.myfavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.thegiphyapp.R

class MyFavoritesFragment : Fragment() {

    private lateinit var myFavoritesViewModel: MyFavoritesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myFavoritesViewModel =
                ViewModelProvider(this).get(MyFavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_myfavorites, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        myFavoritesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}