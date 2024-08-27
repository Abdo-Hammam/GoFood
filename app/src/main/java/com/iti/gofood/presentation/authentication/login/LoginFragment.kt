package com.iti.gofood.presentation.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iti.gofood.R
import com.iti.gofood.databinding.FragmentLoginBinding
import com.iti.gofood.presentation.authentication.viewmodel.LoginFragmentViewModel


class LoginFragment : Fragment() {


    private val viewModel: LoginFragmentViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.navToSignup(view)
        viewModel.loginAsGuest(view, activity,requireContext())
        viewModel.showHidePass(view, context)
        viewModel.clickLogin(view,activity,requireContext(),binding)

    }

}