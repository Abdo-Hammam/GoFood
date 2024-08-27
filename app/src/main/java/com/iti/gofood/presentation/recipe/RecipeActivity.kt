package com.iti.gofood.presentation.recipe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.project_iti.presentation.ui.allMeals.AllMealsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iti.gofood.R
import com.iti.gofood.databinding.ActivityRecipeBinding
import com.iti.gofood.presentation.recipe.about.AboutFragment
import com.iti.gofood.presentation.recipe.details.DetailsFragment
import com.iti.gofood.presentation.recipe.favorite.FavoritesFragment
import com.iti.gofood.presentation.recipe.search.SearchFragment

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, AllMealsFragment())
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

                R.id.navigation_details -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, DetailsFragment())
                        .commit()
                    true
                }

                R.id.navigation_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView3, AboutFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }*/

        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3)?.findNavController()
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController?.navigate(R.id.homeFragment)
                    true
                }
                R.id.navigation_search -> {
                    navController?.navigate(R.id.searchFragment)
                    true
                }
                R.id.navigation_favorites -> {
                    navController?.navigate(R.id.favoritesFragment)
                    true
                }
                R.id.navigation_about -> {
                    navController?.navigate(R.id.aboutFragment)
                    true
                }
                else -> false
            }
        }
    }
}