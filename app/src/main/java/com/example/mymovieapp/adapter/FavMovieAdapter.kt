package com.example.mymovieapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.R
import com.example.mymovieapp.model.FavoriteMovie
import com.example.mymovieapp.model.Movie
import kotlinx.android.synthetic.main.items.view.*

class FavMovieAdapter() :
    RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    var listFavorites = ArrayList<FavoriteMovie>()
        set(list) {
            if (list.size > 0) {
                listFavorites.clear()
            }
            listFavorites.addAll(list)

            notifyDataSetChanged()
        }

    fun addItem(movie: FavoriteMovie) {
        listFavorites.add(movie)
        notifyItemInserted(listFavorites.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return FavMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    inner class FavMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: FavoriteMovie) {
            with(itemView) {

                val posterPath = "https://image.tmdb.org/t/p/w92${movie.posterPath}"

                Glide
                    .with(itemView)
                    .load(posterPath)
                    .into(img_poster)
            }
        }

    }

}