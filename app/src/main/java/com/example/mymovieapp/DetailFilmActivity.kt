package com.example.mymovieapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mymovieapp.database.DatabaseContract
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.database.FavTvHelper
import com.example.mymovieapp.model.FavoriteMovie
import com.example.mymovieapp.model.FavoriteTv
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.Tv
import com.example.mymovieapp.ui.movie.MovieViewModel
import com.example.mymovieapp.ui.tv.TvViewModel
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilmActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_tv_id"
        const val EXTRA_TV_ID = "extra_movie_id"
        const val EXTRA_FAVORITE_MOVIE = "extra_favorite_movie"
        const val EXTRA_FAVORITE_TV = "extra_favorite_tv"
        const val EXTRA_IS_FAVORITE = "extra_is_favorite"
    }

    private lateinit var favMovieHelper: FavMovieHelper
    private lateinit var favTvHelper: FavTvHelper

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvViewModel: TvViewModel

    private lateinit var movie: Movie
    private lateinit var tv: Tv

    private var movieId: Int = 0
    private var tvId: Int = 0
    private var favoriteMovie: FavoriteMovie? = null
    private var favoriteTv: FavoriteTv? = null

    private var isFavorite = false
    private var isMovie = false

    private val addFavText = "Add to Favorite"
    private val removeFavText = "Remove from Favorite"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        favMovieHelper = FavMovieHelper.getInstance(applicationContext)
        favTvHelper = FavTvHelper.getInstance(applicationContext)

        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)

        tvViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvViewModel::class.java)

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        tvId = intent.getIntExtra(EXTRA_TV_ID, 0)
        favoriteMovie = intent.getParcelableExtra(EXTRA_FAVORITE_MOVIE)
        favoriteTv = intent.getParcelableExtra(EXTRA_FAVORITE_TV)
        isFavorite = intent.getBooleanExtra(EXTRA_IS_FAVORITE, false)

        val supActionBarTitle: String

        if (movieId != 0) {

            if (isFavorite) {
                btn_add_favorite.text = removeFavText
                favoriteMovie?.let { setDetailsMovie(it.movieId) }
            } else {
                setDetailsMovie(movieId)
            }

            isMovie = true

            supActionBarTitle = getString(R.string.details_movie)

            getDetailsMovie()

        } else {
            if (isFavorite) {
                btn_add_favorite.text = removeFavText
                favoriteTv?.let { setDetailsTv(it.tvId) }

                isFavorite = true
            } else {
                setDetailsTv(tvId)
            }

            getDetailsTv()

            supActionBarTitle = getString(R.string.details_tv)
        }

        supportActionBar?.title = supActionBarTitle

        btn_add_favorite.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_add_favorite) {

            val itemId: Int
            val itemPoster: String?
            val values = ContentValues()

            val ITEM_ID: String
            val POSTER_PATH: String

            if (isMovie) {
                itemId = movie.id
                itemPoster = movie.posterPath

                ITEM_ID = DatabaseContract.FavMovieColumns.MOVIE_ID
                POSTER_PATH = DatabaseContract.FavMovieColumns.POSTER_PATH

            } else {
                itemId = tv.id
                itemPoster = tv.posterPath

                ITEM_ID = DatabaseContract.FavTvColumns.TV_ID
                POSTER_PATH = DatabaseContract.FavTvColumns.POSTER_PATH
            }

            values.put(ITEM_ID, itemId)
            values.put(POSTER_PATH, itemPoster)

            if (isFavorite) {

                if (isMovie) {
                    favMovieHelper.deleteById(favoriteMovie?.id.toString())
                } else {
                    favTvHelper.deleteById(favoriteTv?.id.toString())
                }

                Toast.makeText(
                    this@DetailFilmActivity,
                    "dihapus dari favorit",
                    Toast.LENGTH_SHORT
                ).show()

                intent.putExtra(EXTRA_IS_FAVORITE, false)

            } else {

                if (isMovie) {
                    favMovieHelper.insert(values)
                } else {
                    favTvHelper.insert(values)
                }

                Toast.makeText(
                    this@DetailFilmActivity,
                    "difavoritkan",
                    Toast.LENGTH_SHORT
                ).show()

                intent.putExtra(EXTRA_IS_FAVORITE, true)
            }

            finish()
            startActivity(intent)
        }
    }

    private fun setDetailsMovie(movieId: Int) {
        val language = getString(R.string.language)

        movieViewModel.setDetailsMovie(movieId, language)
        showLoading(true)
    }

    private fun setDetailsTv(tvId: Int) {
        val language = getString(R.string.language)

        tvViewModel.setDetailsTv(tvId, language)
        showLoading(true)
    }

    private fun getDetailsMovie() {
        movieViewModel.getDetailsMovie().observe(this, Observer { movie ->
            if (movie != null) {
                loadMovieData(movie)
                this.movie = movie
                showLoading(false)
            }
        })
    }

    private fun getDetailsTv() {
        tvViewModel.getDetailsTv().observe(this, Observer { tv ->
            if (tv != null) {
                loadTvData(tv)
                this.tv = tv
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

    private fun loadTvData(tv: Tv) {
        val title = tv.name
        val oriTitle = tv.originalName

        if (title.equals(oriTitle)) {
            tv_title.text = title
        } else {
            val editTitle = "$oriTitle ($title)"
            tv_title.text = editTitle
        }

        val posterPath = "https://image.tmdb.org/t/p/w154" + tv.posterPath
        val backdropPath = "https://image.tmdb.org/t/p/w500" + tv.backdropPath

        Glide
            .with(this)
            .load(posterPath)
            .into(img_poster)

        Glide
            .with(this)
            .load(backdropPath)
            .into(img_backdrop)

        tv_overview.text = tv.overview

        val vote = "${tv.voteAverage}/10"
        tv_vote_average.text = vote
        tv_vote_count.text = tv.voteCount

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
