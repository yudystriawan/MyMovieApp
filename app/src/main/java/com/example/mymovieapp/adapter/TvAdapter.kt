package com.example.mymovieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.DetailFilmActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.database.FavTvHelper
import com.example.mymovieapp.helper.MappingHelper
import com.example.mymovieapp.model.Tv
import kotlinx.android.synthetic.main.items.view.*

class TvAdapter :
    RecyclerView.Adapter<TvAdapter.TvViewHolder>() {

    private val tvShows = ArrayList<Tv>()
    private lateinit var favTvHelper: FavTvHelper

    fun setData(items: ArrayList<Tv>) {
        tvShows.clear()
        tvShows.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(tvShows: Tv) {

            with(itemView) {

                val posterPath = "https://image.tmdb.org/t/p/w92${tvShows.posterPath}"
                Glide
                    .with(itemView)
                    .load(posterPath)
                    .into(img_poster)

                favTvHelper = FavTvHelper.getInstance(context.applicationContext)

                itemView.img_poster.setOnClickListener {

                    val cursor = favTvHelper.queryByMovieId(tvShows.id.toString())
                    val favoriteTv = MappingHelper.getTv(cursor)
                    val isFavorite = favoriteTv.tvId==tvShows.id

                    val intent = Intent(context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_TV_ID, tvShows.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_FAVORITE_TV, favoriteTv)
                    intent.putExtra(DetailFilmActivity.EXTRA_IS_FAVORITE, isFavorite)

                    context.startActivity(intent)

                }

            }

        }

    }
}