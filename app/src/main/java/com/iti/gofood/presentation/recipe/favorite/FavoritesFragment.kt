package com.iti.gofood.presentation.recipe.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti.gofood.R

class FavoritesFragment : Fragment() {
    private val favList: favList by activityViewModels()
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
        val textView: TextView = view.findViewById(R.id.myTextView)
        var numOfItems = 0
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        favList.items.observe(viewLifecycleOwner) { data ->
            numOfItems = data.size
            recyclerView.adapter = ItemAdapter(data) // Update UI when data changes
        }
        if(numOfItems != 0) {
            textView.visibility = View.GONE
        }
        else {
            textView.visibility = View.VISIBLE
        }
    }
}