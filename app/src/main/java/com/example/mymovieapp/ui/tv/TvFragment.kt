package com.example.mymovieapp.ui.tv


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mymovieapp.R
import com.example.mymovieapp.adapter.TvAdapter

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : Fragment() {

    private lateinit var viewModel: TvViewModel
    private lateinit var adapter: TvAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var clTvs: ConstraintLayout

    private lateinit var rvAiringToday: RecyclerView
    private lateinit var rvOnTheAir: RecyclerView
    private lateinit var rvPopular: RecyclerView
    private lateinit var rvTopRated: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        clTvs = view.findViewById(R.id.cl_tvs)

        rvAiringToday = view.findViewById(R.id.rv_airing_today)
        rvOnTheAir = view.findViewById(R.id.rv_on_the_air)
        rvPopular = view.findViewById(R.id.rv_popular)
        rvTopRated = view.findViewById(R.id.rv_top_rated)

        rvAiringToday.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvPopular.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvTopRated.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvOnTheAir.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvViewModel::class.java)

        setDataTvs()

        getDataTvs()

        return view
    }

    private fun setDataTvs() {
        viewModel.setAiringToday()
        viewModel.setOnTheAir()
        viewModel.setPopularTv()
        viewModel.setTopRatedTv()
        showLoading(true)
    }

    private fun getDataTvs() {
        viewModel.getAiringToday().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                adapter = TvAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(tvShows)
                rvAiringToday.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getOnTheAir().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                adapter = TvAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(tvShows)
                rvOnTheAir.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getPopularTv().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                adapter = TvAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(tvShows)
                rvPopular.adapter = adapter
                showLoading(false)
            }
        })

        viewModel.getTopRatedTv().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                adapter = TvAdapter()
                adapter.notifyDataSetChanged()
                adapter.setData(tvShows)
                rvTopRated.adapter = adapter
                showLoading(false)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            clTvs.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            clTvs.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }


}
