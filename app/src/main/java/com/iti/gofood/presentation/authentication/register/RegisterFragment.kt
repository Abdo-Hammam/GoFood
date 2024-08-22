package com.iti.gofood.presentation.authentication.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iti.gofood.databinding.FragmentRegisterBinding
import com.iti.gofood.presentation.authentication.viewmodel.RegisterFragmentViewModel


class RegisterFragment : Fragment() {

    private val viewModel: RegisterFragmentViewModel by viewModels()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navToLogin(view)
        viewModel.checkTerms(resources,binding)
        viewModel.showHidePass(view,context)

        binding.signupBtn.setOnClickListener {
        val check = RegisterInputsValidation(binding)
            if (check.readyToSignup() && !check.isBlankInputs())
                viewModel.insertNewUser(view,context)
            else
               check.correctErrors()
        }



    }
}