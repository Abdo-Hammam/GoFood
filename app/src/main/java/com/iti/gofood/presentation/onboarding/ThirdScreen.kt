package com.iti.gofood.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.iti.gofood.R
import com.iti.gofood.presentation.authentication.AuthActivity

class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.finish).setOnClickListener {
            val intent = Intent(context,AuthActivity::class.java)
            startActivity(intent)
            onBoardingFinished()
            activity?.finish()
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireContext().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finish",true)
        editor.apply()

    }

}