package com.example.project_iti.presentation.ui.allMeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.project_iti.data.Remote.Retrofit.MyRetrofit
import com.example.project_iti.data.Remote.source.RemoteDataSourceImpl
import com.example.project_iti.presentation.ui.adapters.AdapterAllMeals
import com.example.project_iti.repo.Repo
import com.iti.gofood.R
import com.iti.gofood.databinding.FragmentHomeBinding

class AllMealsFragment : Fragment() {
    private val repository by lazy { Repo(RemoteDataSourceImpl(MyRetrofit.service)) }
    private val viewModel : AllMealsViewModel by viewModels(factoryProducer = { AllMealsViewModelFactory(repository) })
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter: AdapterAllMeals by lazy { AdapterAllMeals(emptyList()) }
    val alLetter = arrayOf("A","B","C","D","E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        viewModel.mealList.observe(viewLifecycleOwner) {
            adapter.update(it)
        }
        adapter.setOnItemClickListener {
            findNavController().navigate(AllMealsFragmentDirections.actionHomeFragmentToDetailsFragment3(it.idMeal.toInt()))
        }
        binding.view1.adapter = adapter

        val spinner: Spinner = binding.spinnerMeal

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            alLetter
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLetter = alLetter[position]
                viewModel.getMeals(selectedLetter)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}

