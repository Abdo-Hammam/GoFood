package com.iti.gofood.presentation.recipe.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.project_iti.data.Remote.Retrofit.MealResponse
import com.example.project_iti.repo.Repo
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repo) : ViewModel() {
    private val _mealDetails = MutableLiveData<MealResponse>()
    val mealDetails: LiveData<MealResponse> get() = _mealDetails

    fun fetchMealById(mealId: String) {
        viewModelScope.launch {
            try {
                val list = repository.fetchRecipeById(mealId)
                _mealDetails.postValue(list)
            } catch (e: Exception) {
                e.message
            }
        }
    }
}

class DetailsViewModelFactory(private val repository: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            DetailsViewModel(repository) as T
        else throw Exception("Unknown")
    }
}