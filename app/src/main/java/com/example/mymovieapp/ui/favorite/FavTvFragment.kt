package com.example.mymovieapp.ui.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.FavTvAdapter
import com.example.mymovieapp.database.FavTvHelper
import com.example.mymovieapp.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavTvFragment : Fragment() {

    private lateinit var adapter: FavTvAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var helper: FavTvHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fav_tv, container, false)

        recyclerView = view.findViewById(R.id.rv_fav_tv)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)

        adapter = FavTvAdapter()
        recyclerView.adapter = adapter

        helper = FavTvHelper.getInstance(activity!!.applicationContext)

        loadMovieAsync()

        return view
    }

    private fun loadMovieAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredMovies = async(Dispatchers.IO) {
                val cursor = helper.queryAll()
                MappingHelper.getAllTvs(cursor)
            }

            val tvShows = deferredMovies.await()
            if (tvShows.size > 0) {
                adapter.listFavorites = tvShows
//                adapter.setData(movies)
            } else {
                adapter.listFavorites = ArrayList()
//                adapter.setData(movies)
                Toast.makeText(context, "tidak ada data", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
