package com.iti.gofood.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.iti.gofood.R
import com.iti.gofood.presentation.authentication.AuthActivity
import com.iti.gofood.presentation.recipe.RecipeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            if (onBoardingFinish() && onLoggedIn()) {
                startActivity(Intent(context, RecipeActivity::class.java))
                activity?.finish()
            }else if(onBoardingFinish()) {
                startActivity(Intent(context, AuthActivity::class.java))
                activity?.finish()
            }
            else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }
    }

    private fun onBoardingFinish(): Boolean {
        val sharedPref = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finish", false)
    }

    private fun onLoggedIn(): Boolean {
        val sharedPref = requireContext().getSharedPreferences("onLoggedIn", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("logged", false)
    }


}