package com.iti.gofood.presentation.authentication.viewmodel

import android.app.Application
import android.content.Context
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.iti.gofood.R
import com.iti.gofood.data.localsource.user_db.database.UserDatabase
import com.iti.gofood.data.localsource.user_db.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repo = UserRepository(userDao)
    }


    fun loginAsGuest(view: View, activity: FragmentActivity?, context: Context) {
        view.findViewById<Button>(R.id.guest_login_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_recipeActivity)
            onLoggedIn(context,0)
            activity?.finish()
        }
    }


    fun navToSignup(view: View) {

        val signUpText = view.findViewById<TextView>(R.id.signup_nav)
        val signSpan = SpannableString(signUpText.text)

        val signUp = object : ClickableSpan() {
            override fun onClick(widget: View) {
                view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        signSpan.setSpan(signUp, 23, 30, Spanned.SPAN_USER)
        signUpText.text = signSpan
        signUpText.movementMethod = LinkMovementMethod.getInstance()

    }


    fun loginToApp(view: View, activity: FragmentActivity?, context: Context,onFail: () -> Unit) {
        val email =
            view.findViewById<TextInputEditText>(R.id.email_signin_field)?.text.toString()
        val password =
            view.findViewById<TextInputEditText>(R.id.password_signin_field)?.text.toString()

        viewModelScope.launch(Dispatchers.Main) {
            val isSignedUp = repo.isUserSignedUp(email, password)
            val user = repo.getUser(email,password)
            val userId = user.id

            if (isSignedUp) {
                view.findNavController().navigate(R.id.action_loginFragment_to_recipeActivity)
                activity?.finish()
                onLoggedIn(context,userId)
            } else {
                onFail()
            }
        }
    }


    private fun onLoggedIn(context: Context,userId: Int) {
        val sharedPref = context.getSharedPreferences("onLoggedIn", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("logged", true)
        editor.putInt("userId", userId )
        editor.apply()
    }



    fun showHidePass(view: View, context: Context?) {
        val password = view.findViewById<TextInputEditText>(R.id.password_signin_field)
        val showPass = view.findViewById<Button>(R.id.show_pass)
        showPass.setOnClickListener {
            when (showPass.text) {
                context?.getString(R.string.show_password) -> {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
                    password.setSelection(password.length())
                    showPass.text = context?.getString(R.string.hide_password)
                }

                context?.getString(R.string.hide_password) -> {
                    password.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    password.setSelection(password.length())
                    showPass.text = context?.getString(R.string.show_password)
                }
            }

        }
    }



}