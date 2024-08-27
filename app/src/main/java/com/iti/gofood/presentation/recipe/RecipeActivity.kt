package com.iti.gofood.presentation.recipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.iti.gofood.R
import com.iti.gofood.databinding.ActivityRecipeBinding
import com.iti.gofood.presentation.authentication.AuthActivity

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.title = "GoFood"

        navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3)?.findNavController()
            ?: throw IllegalStateException("NavController not found")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.search -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.favourite -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_screen_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_about -> {
                navController.navigate(R.id.aboutFragment) // Navigate to AboutFragment
                true
            }
            R.id.item_signOut -> {
                signOut(applicationContext)
                startActivity(Intent(applicationContext, AuthActivity::class.java))
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut(context: Context) {
        val sharedPref = context.getSharedPreferences("onLoggedIn", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("logged", false)
        editor.apply()
    }
}
