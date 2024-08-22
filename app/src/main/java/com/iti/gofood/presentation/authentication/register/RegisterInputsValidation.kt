package com.iti.gofood.presentation.authentication.register

import android.util.Patterns
import androidx.core.widget.doOnTextChanged
import com.iti.gofood.databinding.FragmentRegisterBinding

class RegisterInputsValidation(private val binding: FragmentRegisterBinding) {


    init {
        binding.emailSignupField.doOnTextChanged { _, _, _, _ ->
            binding.emailSignupContainer.error = isValidEmail()
        }
            binding.passwordSignupField.doOnTextChanged { _, _, _, _ ->
                binding.passwordSignupContainer.error = isValidPassword()

        }
        binding.firstNameField.doOnTextChanged { _, _, _, _ ->
            binding.firstNameContainer.error = null
        }
        binding.lastNameField.doOnTextChanged { _, _, _, _ ->
            binding.lastNameContainer.error = null
        }
    }


    private fun isValidEmail(): String? {
        val emailText = binding.emailSignupField.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
            return "Invalid Email Address"
        if (emailText.isEmpty())
            return "Email can't be empty"
        return null
    }


    private fun isValidPassword(): String? {
        val passwordText = binding.passwordSignupField.text.toString()

        if (passwordText.length < 6)
            return "Minimum 6 Character Password"

        if (!passwordText.matches(".*[0-9].*".toRegex()))
            return "Must Contain Numbers"

        return null
    }


    fun readyToSignup(): Boolean {
        return binding.emailSignupContainer.error == null && binding.passwordSignupContainer.error == null
    }

    fun isBlankInputs(): Boolean {
        return binding.firstNameField.text.toString()
            .isBlank() || binding.lastNameField.text.toString()
            .isBlank() || binding.emailSignupField.text.toString()
            .isBlank() || binding.passwordSignupField.text.toString().isBlank()
    }


    fun correctErrors() {
        if (binding.firstNameField.text.toString().isBlank())
            binding.firstNameContainer.error = "Required"
        if (binding.lastNameField.text.toString().isBlank())
            binding.lastNameContainer.error = "Required"
        if (binding.emailSignupField.text.toString().isBlank())
            binding.emailSignupContainer.error = "Required"
        if (binding.passwordSignupField.text.toString().isBlank())
            binding.passwordSignupContainer.error = "Required"
    }

}