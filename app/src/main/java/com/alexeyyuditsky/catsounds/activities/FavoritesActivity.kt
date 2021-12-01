package com.alexeyyuditsky.catsounds.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexeyyuditsky.catsounds.R
import com.alexeyyuditsky.catsounds.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity(R.layout.activity_favorites) {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.left_out_close_activity, R.anim.right_in_close_activity)
        super.onBackPressed()
    }

}