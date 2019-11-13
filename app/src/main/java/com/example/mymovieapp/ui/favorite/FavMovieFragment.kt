package com.example.mymovieapp.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.FavMovieAdapter
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fav_movie, container, false)

       return view
    }

    private fun loadMovieAsync() {
//        GlobalScope.launch(Dispatchers.Main){
//            val deferredMovies = async(Dispatchers.IO){
//                val cursor = favMovieHelper.queryAll()
//                MappingHelper.mapCursorToArrayList(cursor)
//            }
//
//            val movies = deferredMovies.await()
//            if (movies.size>0){
//                adapter.listFavorites = movies
//            }else{
//                adapter.listFavorites = ArrayList()
//                Toast.makeText(context, "tidak ada data", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

}
