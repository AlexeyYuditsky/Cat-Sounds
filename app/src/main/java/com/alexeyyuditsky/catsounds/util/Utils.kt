package com.alexeyyuditsky.catsounds.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.alexeyyuditsky.catsounds.data.Cat
import com.alexeyyuditsky.catsounds.R
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.alexeyyuditsky.catsounds.BuildConfig

const val KEY_LIST_PREFERENCES = "listCats"
const val G_PLAY_ADDRESS = "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"

var listFavorites = mutableListOf<Cat>()

fun addToFavoritesList(cat: Cat) {
    if (listFavorites.none { it.title == cat.title }) listFavorites.add(cat)
}

fun removeFromFavoritesList(cat: Cat) = listFavorites.removeAll { it.title == cat.title }

fun rateApp(activity: Activity) {
    try {
        activity.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(G_PLAY_ADDRESS))
        )
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(activity, activity.getString(R.string.no_app_for_open), Toast.LENGTH_SHORT).show()
    }
}

fun selectNumberColumns(activity: Activity): Int {
    val screenLayout = activity.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    val orientation = activity.resources.configuration.orientation
    return when {
        (screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE || screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE)
                && orientation == Configuration.ORIENTATION_LANDSCAPE -> 4
        (screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE || screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE)
                && orientation == Configuration.ORIENTATION_PORTRAIT -> 3
        screenLayout == Configuration.SCREENLAYOUT_SIZE_NORMAL
                && orientation == Configuration.ORIENTATION_LANDSCAPE -> 3
        else -> 2
    }
}

@SuppressLint("InflateParams")
fun showSnackbar(
    viewBinging: ViewBinding,
    activity: Activity,
    inflater: LayoutInflater,
    color: Int,
    image: Int,
    text: Int,
    catTitle: Int
) {
    val snackbar = Snackbar.make(viewBinging.root, "", Snackbar.LENGTH_SHORT)
    val layout = snackbar.view as Snackbar.SnackbarLayout
    val textViewOld = layout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textViewOld.visibility = View.INVISIBLE

    val customSnackbar = inflater.inflate(R.layout.snackbar, null)
    val imageView = customSnackbar.findViewById<ImageView>(R.id.imageView)
    val textView = customSnackbar.findViewById<TextView>(R.id.textView)
    customSnackbar.setBackgroundColor(ContextCompat.getColor(activity, color))
    imageView.setImageResource(image)
    textView.text = activity.getString(text, activity.getString(catTitle))

    layout.setPadding(0, 0, 0, 0)
    layout.addView(customSnackbar, 0)
    snackbar.show()
}