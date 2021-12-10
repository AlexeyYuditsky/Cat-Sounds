package com.alexeyyuditsky.catsounds.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alexeyyuditsky.catsounds.data.Cat
import com.alexeyyuditsky.catsounds.fragment.CatFragment
import com.alexeyyuditsky.catsounds.util.KEY_LIST_PREFERENCES

class CatFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = Cat.listCat().size

    override fun createFragment(position: Int):Fragment {
        return CatFragment().apply {
            arguments = bundleOf(KEY_LIST_PREFERENCES to Cat.listCat()[position])
        }

    }
}
