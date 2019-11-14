package com.example.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.R
import com.example.mymovieapp.model.FavoriteMovie
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_grid, parent, false)
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

                val posterPath = "https://image.tmdb.org/t/p/w342${movie.posterPath}"

                Glide
                    .with(itemView)
                    .load(posterPath)
                    .into(img_poster)
            }
        }

    }

}