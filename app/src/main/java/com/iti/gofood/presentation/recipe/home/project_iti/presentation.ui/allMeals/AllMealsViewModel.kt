package com.example.project_iti.presentation.ui.allMeals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.project_iti.data.Remote.Retrofit.Meal
import com.example.project_iti.repo.Repo
import kotlinx.coroutines.launch

class AllMealsViewModel(private val repository: Repo) : ViewModel() {
    private val _mealsList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> get() = _mealsList

    init {
        getMeals("A")
    }

    fun getMeals(letter: String) {
        viewModelScope.launch {
            val list = repository.getAllMeals(letter)
            _mealsList.postValue(list ?: emptyList())
        }
    }
}


class AllMealsViewModelFactory(private val repository: Repo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AllMealsViewModel::class.java))
            AllMealsViewModel(repository) as T
        else throw Exception("Unknown")
    }
}
