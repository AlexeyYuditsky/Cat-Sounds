package com.alexeyyuditsky.catsounds.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alexeyyuditsky.catsounds.adapters.CatRecyclerAdapter
import com.alexeyyuditsky.catsounds.data.Cat
import com.alexeyyuditsky.catsounds.databinding.FragmentCatBinding
import com.alexeyyuditsky.catsounds.util.KEY_LIST_PREFERENCES
import com.alexeyyuditsky.catsounds.util.selectNumberColumns

class CatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCatBinding.inflate(inflater, container, false).apply {
            @Suppress("UNCHECKED_CAST")
            val listCat = arguments?.get(KEY_LIST_PREFERENCES) as List<Cat>
            recyclerView.adapter =
                CatRecyclerAdapter(listCat, inflater = layoutInflater, activity = activity)
            recyclerView.layoutManager =
                GridLayoutManager(context, selectNumberColumns(requireActivity()))
        }.root
    }

}