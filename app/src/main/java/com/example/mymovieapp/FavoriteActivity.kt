package com.example.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "My Favorite"

        val pagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)

    }
}
