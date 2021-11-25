package com.alexeyyuditsky.catsounds.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.alexeyyuditsky.catsounds.R
import com.alexeyyuditsky.catsounds.databinding.ActivityMainBinding

const val KEY_THEME_PREFERENCES = "theme"
const val darkTheme = 2
const val lightTheme = 1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPreferences: SharedPreferences by lazy { getPreferences(Context.MODE_PRIVATE) }
    private val toast by lazy { Toast.makeText(this, getText(R.string.toast_message), Toast.LENGTH_SHORT) }
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setTheme() {
        if (sharedPreferences.getInt(KEY_THEME_PREFERENCES, 0) == darkTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if (sharedPreferences.getInt(KEY_THEME_PREFERENCES, 0) == lightTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            toast.cancel()
            super.onBackPressed()
        } else toast.show()
        backPressedTime = System.currentTimeMillis()
    }

}