package com.example.mymovieapp.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.FavTvAdapter
import com.example.mymovieapp.database.FavTvHelper
import com.example.mymovieapp.helper.MappingHelper
import com.example.mymovieapp.model.FavoriteTv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavTvFragment : Fragment() {

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

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

        if (savedInstanceState == null) {
            loadTvAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FavoriteTv>(EXTRA_STATE)
            if (list != null) {
                adapter.listFavorites = list
            }
        }

        return view
    }

    private fun loadTvAsync() {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFavorites)
    }


}
