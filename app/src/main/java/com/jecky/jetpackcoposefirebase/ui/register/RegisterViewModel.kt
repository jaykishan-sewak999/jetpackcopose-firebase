package com.jecky.jetpackcoposefirebase.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: AuthRepository) : ViewModel() {

    //    var authResponse: UsersAuthResponse by mutableStateOf(UsersAuthResponse())
    var showLoading: Boolean by mutableStateOf(false)

    private var _doLogin: MutableLiveData<AuthResult> = MutableLiveData<AuthResult>()
    var doLogin: LiveData<AuthResult> = _doLogin

    fun doLogin(email: String, password: String) {
        showLoading = true
        viewModelScope.launch {
            when (val response = repository.register(email, password)) {
                is APIResult.APISuccess -> {
                    _doLogin.value = response.data
                    showLoading = false
                }

                is APIResult.APIFailure -> {
                    showLoading = false
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