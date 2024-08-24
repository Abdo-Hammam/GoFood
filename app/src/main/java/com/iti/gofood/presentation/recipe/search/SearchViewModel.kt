package com.example.project_iti.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.project_iti.data.Remote.Retrofit.Meal
import com.example.project_iti.repo.Repo
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repo) : ViewModel() {
    private val _mealsList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> get() = _mealsList

    fun searchMeals(query: String) {
        viewModelScope.launch {
            val list = repository.searchMeals(query)
            _mealsList.postValue(list ?: emptyList())
        }
    }
}

class SearchViewModelFactory(private val repository: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java))
            SearchViewModel(repository) as T
        else throw Exception("Unknown ViewModel class")
    }
}

