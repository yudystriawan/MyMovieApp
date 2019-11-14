package com.example.mymovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val title = getString(R.string.my_favorite)

        supportActionBar?.title = title

        val pagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}
