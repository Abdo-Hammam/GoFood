package com.iti.gofood.presentation.recipe.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _myData = MutableLiveData<List<Item>>()

    fun addItemToFav(newItem: Item) {
        val currentList = _myData.value ?: emptyList() // Get current list or an empty list
        val updatedList = currentList.toMutableList() // Create a mutable copy
        updatedList.add(newItem) // Modify the copy
        _myData.value = updatedList // Update the LiveData
    }

    fun removeItemToFav(item: Item) {
        val currentList = _myData.value ?: emptyList() // Get current list or an empty list
        val updatedList = currentList.toMutableList() // Create a mutable copy
        updatedList.remove(item) // Modify the copy
        _myData.value = updatedList // Update the LiveData
    }

    val items: LiveData<List<Item>> = _myData
}