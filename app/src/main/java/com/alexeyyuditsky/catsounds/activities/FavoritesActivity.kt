package com.alexeyyuditsky.catsounds.activities

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.alexeyyuditsky.catsounds.R
import com.alexeyyuditsky.catsounds.adapters.CatRecyclerAdapter
import com.alexeyyuditsky.catsounds.data.Cat
import com.alexeyyuditsky.catsounds.databinding.ActivityAboutBinding
import com.alexeyyuditsky.catsounds.databinding.ActivityFavoritesBinding
import com.alexeyyuditsky.catsounds.util.KEY_LIST_PREFERENCES
import com.alexeyyuditsky.catsounds.util.listFavorites
import com.alexeyyuditsky.catsounds.util.selectNumberColumns
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private val listenerVisibility: (Int) -> Unit = { binding.imageView.visibility = it }
    private val sharedPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater).also { setContentView(it.root) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setFavorites()

        binding.recyclerView.adapter =
            CatRecyclerAdapter(listFavorites, listenerVisibility, layoutInflater, this)
        binding.recyclerView.layoutManager = GridLayoutManager(this, selectNumberColumns(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_favorites, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(
                    R.anim.left_out_close_activity,
                    R.anim.right_in_close_activity
                )
                return true
            }
            R.id.action_delete_all -> {
                showAlertDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog() {
        if (listFavorites.isNotEmpty()) {

            class MyDialogFragment : DialogFragment() {
                private val favoritesActivity by lazy { activity as FavoritesActivity }

                override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                    return AlertDialog.Builder(requireContext()).run {
                        setMessage(R.string.my_dialog_fragment_message)
                            .setPositiveButton(android.R.string.ok) { _, _ ->
                                favoritesActivity.confirmDeletion()
                                favoritesActivity.showSnackbar()
                            }
                            .setNegativeButton(android.R.string.cancel) { _, _ -> dismiss() }
                        create()
                    }
                }
            }

            MyDialogFragment().show(supportFragmentManager, null)
        }
    }

    private fun showSnackbar() = com.alexeyyuditsky.catsounds.util.showSnackbar(
        viewBinging = binding,
        activity = this,
        inflater = layoutInflater,
        color = R.color.red,
        image = R.drawable.delete,
        text = R.string.snackbar_delete_favorites,
        catTitle = R.string.snackbar_delete_favorites
    )

    override fun onStop() {
        val json = Gson().toJson(listFavorites)
        sharedPreferences.edit().putString(KEY_LIST_PREFERENCES, json).apply()
        super.onStop()
    }

    private fun setFavorites() {
        if (listFavorites.isEmpty()) {
            listFavorites = if (sharedPreferences.getString(KEY_LIST_PREFERENCES, "") != "") {
                val json2 = sharedPreferences.getString(KEY_LIST_PREFERENCES, "")
                val catType = object : TypeToken<MutableList<Cat>>() {}.type
                Gson().fromJson(json2, catType)
            } else listFavorites
        }
        if (listFavorites.isEmpty()) binding.imageView.visibility = View.VISIBLE
        else binding.imageView.visibility = View.GONE
    }

    fun confirmDeletion() {
        listFavorites.clear()
        binding.recyclerView.adapter = CatRecyclerAdapter(listFavorites)
        binding.imageView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.left_out_close_activity, R.anim.right_in_close_activity)
        super.onBackPressed()
    }

}