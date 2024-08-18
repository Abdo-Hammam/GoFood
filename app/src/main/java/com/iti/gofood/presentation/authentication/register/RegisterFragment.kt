package com.iti.gofood.presentation.authentication.register

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.iti.gofood.R


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navToLogin(view)
    }

    private fun navToLogin(view: View){

        val loginText = view.findViewById<TextView>(R.id.login_nav)
        val signSpan = SpannableString(loginText.text)

        val signUp = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        signSpan.setSpan(signUp,25,32, Spanned.SPAN_USER)
        loginText.text = signSpan
        loginText.movementMethod = LinkMovementMethod.getInstance()

    }

}