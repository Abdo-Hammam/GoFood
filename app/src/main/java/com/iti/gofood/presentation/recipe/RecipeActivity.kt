package com.iti.gofood.presentation.recipe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iti.gofood.R
import com.iti.gofood.presentation.recipe.favorite.FavoritesFragment
import com.iti.gofood.presentation.recipe.home.HomeFragment
import com.iti.gofood.presentation.recipe.search.SearchFragment

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, HomeFragment())
                        .commit()
                    true
                }

                R.id.navigation_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, SearchFragment())
                        .commit()
                    true
                }

                R.id.navigation_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, FavoritesFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}