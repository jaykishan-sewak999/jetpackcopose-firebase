package com.jecky.jetpackcoposefirebase.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.jecky.jetpackcoposefirebase.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository):ViewModel() {

    var loading: Boolean by mutableStateOf(false)
    var loginState: Int by mutableIntStateOf(0)

    private var _doLogin: MutableLiveData<AuthResult> = MutableLiveData()
    var doLogin: LiveData<AuthResult> = _doLogin

    fun doLogin(email: String, password: String){
        loading = true
        try {
            viewModelScope.launch {
                val result = authRepository.login(email,password)
                loading = false
                loginState = 1
                _doLogin.value = result.data
            }
        }
        catch (exception: Exception){
            loading = false
            loginState = 2
            _doLogin.value = null
        }
    }
}

class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(AuthRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}