package com.example.mymovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.R
import com.example.mymovieapp.model.FavoriteTv
import kotlinx.android.synthetic.main.items.view.*

class FavTvAdapter() :
    RecyclerView.Adapter<FavTvAdapter.FavTvViewHolder>() {

    var listFavorites = ArrayList<FavoriteTv>()
        set(list) {
            if (list.size > 0) {
                listFavorites.clear()
            }
            listFavorites.addAll(list)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_grid, parent, false)
        return FavTvViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTvViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    inner class FavTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: FavoriteTv) {
            with(itemView) {

                val posterPath = "https://image.tmdb.org/t/p/w342${tv.posterPath}"

                Glide
                    .with(itemView)
                    .load(posterPath)
                    .into(img_poster)
            }
        }

    }

}