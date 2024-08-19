package com.iti.gofood.presentation.authentication.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.iti.gofood.R
import com.iti.gofood.presentation.recipe.RecipeActivity


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navToSignup(view)
        }

    private fun navToSignup(view: View){

        val signUpText = view.findViewById<TextView>(R.id.signup_nav)
        val signSpan = SpannableString(signUpText.text)

        val signUp = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        signSpan.setSpan(signUp,23,30, Spanned.SPAN_USER)
        signUpText.text = signSpan
        signUpText.movementMethod = LinkMovementMethod.getInstance()

        view.findViewById<Button>(R.id.guest_login_btn).setOnClickListener {
            startActivity(Intent(context, RecipeActivity::class.java))
            activity?.finish()
        }

    }

}