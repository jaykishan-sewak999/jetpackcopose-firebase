package com.jecky.jetpackcoposefirebase.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.network.UsersListResponse
import com.jecky.jetpackcoposefirebase.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: AuthRepository) : ViewModel() {

    val shouldShowLoginLoader: Boolean by mutableStateOf(false)
    private val mutableState = MutableStateFlow<APIResult<*>>(APIResult.NONE)
    val state = mutableState.asStateFlow()
    fun doLogin(email: String, password: String) {

        /*viewModelScope.launch {
            when (val response = repository.register(email, password)) {
                is APIResult.APISuccess<*> -> {
                        //mutableState.value = response.data!!
                }
                is APIResult.APIFailure<*> -> {

                }
            }
        }*/

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