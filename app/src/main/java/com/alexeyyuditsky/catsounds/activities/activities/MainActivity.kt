package com.alexeyyuditsky.catsounds.activities.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.alexeyyuditsky.catsounds.R
import com.alexeyyuditsky.catsounds.activities.adapters.CatFragmentStateAdapter
import com.alexeyyuditsky.catsounds.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

const val KEY_THEME_PREFERENCES = "theme"
const val darkTheme = 2
const val lightTheme = 1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPreferences: SharedPreferences by lazy { getPreferences(Context.MODE_PRIVATE) }
    private val toast by lazy {
        Toast.makeText(
            this,
            getText(R.string.toast_message),
            Toast.LENGTH_SHORT
        )
    }
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.viewPager2.adapter = CatFragmentStateAdapter(this)
        val listNameTabs =
            listOf(getString(R.string.meow), getString(R.string.angry), getString(R.string.newborn))
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = listNameTabs[position]
        }.attach()
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