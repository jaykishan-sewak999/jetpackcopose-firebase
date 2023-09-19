package com.jecky.jetpackcoposefirebase.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.jecky.jetpackcoposefirebase.model.APIRESULT
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.model.UsersAuthResponse
import com.jecky.jetpackcoposefirebase.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: AuthRepository) : ViewModel() {

    var authResponse: UsersAuthResponse by mutableStateOf(UsersAuthResponse())
    var showLoading: Boolean by mutableStateOf(false)

    fun doLogin(email: String, password: String) {
        showLoading = true
        viewModelScope.launch {
            when (val response = repository.register(email, password)) {
                is APIResult.APISuccess<*> -> {
                    authResponse = response.data!!
                    authResponse.status = APIRESULT.SUCCESS.name
                    showLoading = false
                }
                is APIResult.APIFailure<*> -> {
                    showLoading = false
                    authResponse.status = APIRESULT.FAIL.name
                    authResponse.message = response.errorMessage
                }

                else -> {}
            }
        }

    }
}

class RegisterViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(AuthRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}