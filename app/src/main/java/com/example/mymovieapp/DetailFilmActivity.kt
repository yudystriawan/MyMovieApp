package com.example.mymovieapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovieapp.adapter.FavMovieAdapter
import com.example.mymovieapp.database.DatabaseContract
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.model.FavoriteMovie
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.ui.favorite.FavMovieFragment
import com.example.mymovieapp.ui.movie.MovieViewModel
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    private lateinit var favMovieHelper: FavMovieHelper
    private lateinit var movieViewModel: MovieViewModel

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        favMovieHelper = FavMovieHelper.getInstance(applicationContext)

        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)

        setDetailsMovie(movieId)
        getDetailsMovie()


        btn_add_favorite.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_add_favorite) {

        }
    }

    private fun setDetailsMovie(movieId: Int) {
        movieViewModel.setDetailsMovie(movieId)
        showLoading(true)
    }

    private fun getDetailsMovie() {
        movieViewModel.getDetailsMovie().observe(this, Observer { movie ->
            if (movie != null) {
                loadMovieData(movie)
                showLoading(false)
            }
        })
    }

    private fun loadMovieData(movie: Movie) {
        val title = movie.title
        val oriTitle = movie.originalTitle

        if (title.equals(oriTitle)) {
            tv_title.text = title
        } else {
            val editTitle = "$oriTitle ($title)"
            tv_title.text = editTitle
        }

        val posterPath = "https://image.tmdb.org/t/p/w154" + movie.posterPath
        val backdropPath = "https://image.tmdb.org/t/p/w500" + movie.backdropPath

        Glide
            .with(this)
            .load(posterPath)
            .into(img_poster)

        Glide
            .with(this)
            .load(backdropPath)
            .into(img_backdrop)

        tv_overview.text = movie.overview

        val vote = "${movie.voteAverage}/10"
        tv_vote_average.text = vote
        tv_vote_count.text = movie.voteCount

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            sc_details.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            sc_details.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

}
