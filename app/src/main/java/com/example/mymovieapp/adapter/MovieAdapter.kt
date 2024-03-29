package com.example.mymovieapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.DetailFilmActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.helper.MappingHelper
import com.example.mymovieapp.model.Movie
import kotlinx.android.synthetic.main.items.view.*

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()
    private lateinit var favMovieHelper: FavMovieHelper



    fun setData(items: ArrayList<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {

            with(itemView) {

                val posterPath = "https://image.tmdb.org/t/p/w92${movie.posterPath}"
                Glide
                    .with(itemView)
                    .load(posterPath)
                    .into(img_poster)

                favMovieHelper = FavMovieHelper.getInstance(context.applicationContext)

                itemView.img_poster.setOnClickListener {

                    val cursor = favMovieHelper.queryByMovieId(movie.id.toString())
                    val favoriteMovie = MappingHelper.getMovie(cursor)
                    val isFavorite = favoriteMovie.movieId==movie.id

                    val intent = Intent(context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_MOVIE_ID, movie.id)
                    intent.putExtra(DetailFilmActivity.EXTRA_FAVORITE_MOVIE, favoriteMovie)
                    intent.putExtra(DetailFilmActivity.EXTRA_IS_FAVORITE, isFavorite)

                    context.startActivity(intent)

                }

            }

        }

    }
}
