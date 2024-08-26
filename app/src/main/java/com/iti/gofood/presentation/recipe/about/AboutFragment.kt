package com.iti.gofood.presentation.recipe.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.iti.gofood.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the UI elements
        val creatorTextView = view.findViewById<TextView>(R.id.about_creator_name)
        val synopsisTextView = view.findViewById<TextView>(R.id.about_synopsis2)

        // Set dynamic content if needed
        creatorTextView.text = "Creator: Abdelrahman Gamal"
        synopsisTextView.text = "This application helps users manage their recipes and ingredients efficiently."
    }
}
