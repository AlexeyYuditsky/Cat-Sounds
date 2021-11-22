package com.alexeyyuditsky.catsounds.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.alexeyyuditsky.catsounds.databinding.ActivityMainBinding

const val KEY_THEME_PREFERENCES = "theme"
const val darkTheme = 2
const val lightTheme = 1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPreferences: SharedPreferences by lazy { getPreferences(Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun setTheme() {
        if (sharedPreferences.getInt(KEY_THEME_PREFERENCES, 0) == darkTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if (sharedPreferences.getInt(KEY_THEME_PREFERENCES, 0) == lightTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}