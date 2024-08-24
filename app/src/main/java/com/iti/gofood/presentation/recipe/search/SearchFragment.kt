package com.iti.gofood.presentation.recipe.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.project_iti.data.Remote.Retrofit.MyRetrofit
import com.example.project_iti.data.Remote.source.RemoteDataSourceImpl
import com.example.project_iti.presentation.ui.adapters.AdapterAllMeals
import com.example.project_iti.presentation.ui.search.SearchViewModel
import com.example.project_iti.presentation.ui.search.SearchViewModelFactory
import com.example.project_iti.repo.Repo
import com.iti.gofood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(Repo(
        RemoteDataSourceImpl(MyRetrofit.service)
    )) }
    private val adapter: AdapterAllMeals by lazy { AdapterAllMeals(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.view1.adapter = adapter

        viewModel.mealList.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        binding.searchBox.addTextChangedListener { text ->
            viewModel.searchMeals(text.toString())
        }
    }
}
