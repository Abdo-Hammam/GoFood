package com.iti.gofood.presentation.authentication.viewmodel

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.iti.gofood.R
import com.iti.gofood.data.localsource.user_db.database.UserDatabase
import com.iti.gofood.data.localsource.user_db.entity.User
import com.iti.gofood.data.localsource.user_db.repository.UserRepository
import com.iti.gofood.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: UserRepository


    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repo = UserRepository(userDao)
    }

    private fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertUser(user)
        }
    }

    // animation when the user is successfully signed up
    private fun popupDialog(myDialog: Dialog?, view: View?){
        viewModelScope.launch(Dispatchers.Main) {
            myDialog?.setContentView(R.layout.popup_layout)
            myDialog?.show()
            myDialog?.window?.decorView?.setBackgroundResource(R.drawable.dialog_bg)
            myDialog?.findViewById<Button>(R.id.login_popup_btn)?.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_registerFragment_to_loginFragment)
                myDialog.dismiss()
            }

        }
    }


    // to insert new user to database
    fun insertNewUser(view: View,context: Context?) {
        CoroutineScope(Dispatchers.Main).launch {
            val firstName =
                view.findViewById<TextInputEditText>(R.id.first_name_field)?.text
            val lastName =
                view.findViewById<TextInputEditText>(R.id.last_name_field)?.text
            val email =
                view.findViewById<TextInputEditText>(R.id.email_signup_field)?.text
            val password =
                view.findViewById<TextInputEditText>(R.id.password_signup_field)?.text
            val dialog: Dialog? = context?.let { Dialog(it) }

            val user = User(0, firstName.toString(), lastName.toString(), email.toString(), password.toString())
            addUser(user)

            firstName?.clear()
            lastName?.clear()
            email?.clear()
            password?.clear()

            view.findViewById<TextInputLayout>(R.id.email_signup_container).error = null
            view.findViewById<TextInputLayout>(R.id.password_signup_container).error = null


            launch {
                popupDialog(dialog, view)
            }

        }
    }

    // navigate to login
    fun navToLogin(view: View) {

        val loginText = view.findViewById<TextView>(R.id.login_nav)
        val signSpan = SpannableString(loginText.text)

        val signUp = object : ClickableSpan() {
            override fun onClick(widget: View) {
                view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        signSpan.setSpan(signUp, 25, 32, Spanned.SPAN_USER)
        loginText.text = signSpan
        loginText.movementMethod = LinkMovementMethod.getInstance()

    }

    fun checkTerms(resources: Resources, binding: FragmentRegisterBinding) {
        binding.termsCheckbox.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                binding.signupBtn.isEnabled = true
                binding.signupBtn.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_color,
                        null
                    )
                )
            } else {
                binding.signupBtn.isEnabled = false
                binding.signupBtn.setBackgroundColor(resources.getColor(R.color.disabled, null))
            }
        }
    }


}