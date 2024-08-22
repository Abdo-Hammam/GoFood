package com.iti.gofood.presentation.authentication.login

import com.iti.gofood.databinding.FragmentLoginBinding

class LoginInputsValidation(private val binding: FragmentLoginBinding) {


    fun readyToLogin(): Boolean {
        return binding.emailSigninContainer.error == null && binding.passwordSigninContainer.error == null
    }

    fun isBlankInputs(): Boolean {
        return binding.emailSigninField.text.toString()
            .isBlank() || binding.passwordSigninField.toString().isBlank()
    }


    fun correctErrors() {
        if (binding.emailSigninField.text.toString().isBlank())
            binding.emailSigninContainer.error = "Required"
        else binding.emailSigninContainer.error = "Incorrect email or password"
        if (binding.emailSigninField.text.toString().isBlank())
            binding.passwordSigninContainer.error = "Required"
        else binding.passwordSigninContainer.error = "Incorrect email or password"

    }

}