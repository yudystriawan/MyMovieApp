package com.example.mymovieapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieapp.database.FavMovieHelper
import com.example.mymovieapp.database.FavTvHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var favMovieHelper: FavMovieHelper
    private lateinit var favTvHelper: FavTvHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        favMovieHelper = FavMovieHelper.getInstance(applicationContext)
        favTvHelper = FavTvHelper.getInstance(applicationContext)
        favMovieHelper.open()
        favTvHelper.open()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.profile_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        favMovieHelper.close()
        favTvHelper.close()
    }
}
