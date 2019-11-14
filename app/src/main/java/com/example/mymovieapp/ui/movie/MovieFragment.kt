package com.example.mymovieapp.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.MovieAdapter

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    private lateinit var adapter: MovieAdapter

    private lateinit var progressBar: ProgressBar

    private lateinit var clMovies: ConstraintLayout

    private lateinit var rvNowPlaying: RecyclerView
    private lateinit var rvPopular: RecyclerView
    private lateinit var rvTopRated: RecyclerView
    private lateinit var rvUpcoming: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        progressBar = view.findViewById(R.id.progressBar)

        clMovies = view.findViewById(R.id.cl_movies)

        rvNowPlaying = view.findViewById(R.id.rv_now_playing)
        rvPopular = view.findViewById(R.id.rv_popular)
        rvTopRated = view.findViewById(R.id.rv_top_rated)
        rvUpcoming = view.findViewById(R.id.rv_upcoming)

        rvNowPlaying.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvTopRated.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvUpcoming.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)

        setDataMovies()

        getDataMovies()

        return view
    }

    private fun getDataMovies() {
        viewModel.getNowPlayingMovies().observe(this, Observer { movies ->
            if (movies != null) {
                adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(movies)
                rvNowPlaying.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getPopularMovies().observe(this, Observer { movies ->
            if (movies != null) {
                adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(movies)
                rvPopular.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getTopRatedMovies().observe(this, Observer { movies ->
            if (movies != null) {
                adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(movies)
                rvTopRated.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getUpcomingMovies().observe(this, Observer { movies ->
            if (movies != null) {
                adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(movies)
                rvUpcoming.adapter = adapter
                showLoading(false)
            }
        })
    }

    private fun setDataMovies() {
        val language = getString(R.string.language)

        viewModel.setNowPlayingMovies(language)
        viewModel.setPopularMovies(language)
        viewModel.setTopRatedMovies(language)
        viewModel.setUpcomingMovies(language)
        showLoading(true)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            clMovies.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            clMovies.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

}
