package com.jecky.jetpackcoposefirebase.ui.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jecky.jetpackcoposefirebase.repository.DashboardRepository
import com.jecky.jetpackcoposefirebase.repository.model.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: DashboardRepository) : ViewModel() {

    var loading: Boolean by mutableStateOf(false)


    private var _getCategories: MutableLiveData<List<Category>> = MutableLiveData()
    var categories: LiveData<List<Category>> = _getCategories

    fun getCategories() {
        loading = true
        try {
            viewModelScope.launch {
                val result = categoryRepository.getCategories()
                loading = false
                _getCategories.value = result.data
            }
        } catch (exception: Exception) {
            loading = false
            _getCategories.value = null
        }
    }
}

class CategoryViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(DashboardRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}