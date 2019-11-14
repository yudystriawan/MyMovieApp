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
import com.example.mymovieapp.adapter.FavMovieAdapter
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.helper.MappingHelper
import com.example.mymovieapp.model.FavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavMovieFragment : Fragment() {

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    private lateinit var adapter: FavMovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var favMovieHelper: FavMovieHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fav_movie, container, false)

        recyclerView = view.findViewById(R.id.rv_fav_movie)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)

        adapter = FavMovieAdapter()
        recyclerView.adapter = adapter

        favMovieHelper = FavMovieHelper.getInstance(activity!!.applicationContext)

        if (savedInstanceState == null) {
            loadMovieAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FavoriteMovie>(EXTRA_STATE)
            if (list != null) {
                adapter.listFavorites = list
            }
        }

        return view
    }

    private fun loadMovieAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredMovies = async(Dispatchers.IO) {
                val cursor = favMovieHelper.queryAll()
                MappingHelper.getAllMovies(cursor)
            }

            val movies = deferredMovies.await()
            if (movies.size > 0) {
                adapter.listFavorites = movies
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
