package com.alexeyyuditsky.catsounds.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.PopupMenu
import com.alexeyyuditsky.catsounds.data.Cat
import com.alexeyyuditsky.catsounds.databinding.ItemListBinding
import com.alexeyyuditsky.catsounds.util.addToFavoritesList
import com.alexeyyuditsky.catsounds.util.listFavorites
import com.alexeyyuditsky.catsounds.util.removeFromFavoritesList
import com.alexeyyuditsky.catsounds.util.showSnackbar
import android.graphics.BitmapFactory
import com.alexeyyuditsky.catsounds.R

class CatRecyclerAdapter(
    private val listCat: List<Cat>,
    private val listenerVisibility: ((Int) -> Unit)? = null,
    private val inflater: LayoutInflater? = null,
    private val activity: Activity? = null,
) : RecyclerView.Adapter<CatRecyclerAdapter.ViewHolder>() {
    private val options = BitmapFactory.Options()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemListBinding.bind(itemView)
        private var mediaPlayer: MediaPlayer? = null

        fun bind(cat: Cat) {
            viewBinding.cardView.setOnClickListener { createMediaPlayer(cat) }
            viewBinding.cardView.setOnLongClickListener { createPopupMenu(it, cat) }
            setImageBitmap(cat)
            viewBinding.textView.setText(cat.title)
        }

        private fun setImageBitmap(cat: Cat) {
            options.inScaled = false
            val bm = BitmapFactory.decodeResource(activity?.resources, cat.image, options)
            viewBinding.imageView.setImageBitmap(bm)
        }

        private fun createMediaPlayer(cat: Cat) {
            mediaPlayer = MediaPlayer.create(itemView.context, cat.sound)
            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                it.release()
                mediaPlayer = null
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        private fun createPopupMenu(v: View, cat: Cat): Boolean {
            val popupMenu = PopupMenu(itemView.context, v)

            if (listFavorites.none { it.title == cat.title }) popupMenu.inflate(R.menu.menu_popup_activity_main)
            else popupMenu.inflate(R.menu.menu_popup_activity_favorites)

            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.add_favorites) {
                    addToFavoritesList(cat)
                    showSnackbar(
                        viewBinging = viewBinding,
                        activity = activity!!,
                        inflater = inflater!!,
                        color = R.color.green,
                        image = R.drawable.done,
                        text = R.string.snackbar_done,
                        catTitle = cat.title
                    )
                    return@setOnMenuItemClickListener true
                }
                if (item.itemId == R.id.remove_favorites) {
                    removeFromFavoritesList(cat)
                    showSnackbar(
                        viewBinging = viewBinding,
                        activity = activity!!,
                        inflater = inflater!!,
                        color = R.color.red,
                        image = R.drawable.delete,
                        text = R.string.snackbar_delete,
                        catTitle = cat.title
                    )
                    notifyDataSetChanged()
                    if (listFavorites.isEmpty()) listenerVisibility?.invoke(View.VISIBLE)
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false
            }
            popupMenu.show()
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listCat[position])
    override fun getItemCount() = listCat.size
}