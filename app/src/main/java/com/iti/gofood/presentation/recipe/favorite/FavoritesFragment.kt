package com.iti.gofood.presentation.recipe.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti.gofood.R

class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val items = listOf(
            Item(R.drawable.ic_launcher_background, "Title 1", "Description 1"),
            Item(R.drawable.ic_launcher_foreground, "Title 2", "Description 2"),
            Item(R.drawable.ic_launcher_background, "Title 3", "Description 1"),
            Item(R.drawable.ic_launcher_foreground, "Title 4", "Description 2"),
            Item(R.drawable.ic_launcher_background, "Title 5", "Description 1"),
            Item(R.drawable.ic_launcher_foreground, "Title 6", "Description 2"),
            Item(R.drawable.ic_launcher_background, "Title 7", "Description 1"),
            Item(R.drawable.ic_launcher_foreground, "Title 8", "Description 2"),
            Item(R.drawable.ic_launcher_background, "Title 9", "Description 1"),
            Item(R.drawable.ic_launcher_foreground, "Title 10", "Description 2")
            // Add more items here
        )
        recyclerView.adapter = ItemAdapter(items)
    }
}