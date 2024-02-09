package com.jecky.jetpackcoposefirebase.ui.register

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
import com.jecky.jetpackcoposefirebase.network.APIResult
import com.jecky.jetpackcoposefirebase.repository.AuthRepository
import com.jecky.jetpackcoposefirebase.repository.UserRepository
import com.jecky.jetpackcoposefirebase.repository.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: AuthRepository, val userRepository: UserRepository) : ViewModel() {

    //    var authResponse: UsersAuthResponse by mutableStateOf(UsersAuthResponse())
    var showLoading: Boolean by mutableStateOf(false)
    var loginState: Int by mutableIntStateOf(0)

    private var _doRegister: MutableLiveData<AuthResult> = MutableLiveData<AuthResult>()
    var doRegister: LiveData<AuthResult> = _doRegister

    fun doLogin(email: String, password: String) {
        showLoading = true
        viewModelScope.launch {
            when (val response = repository.register(email, password)) {
                is APIResult.APISuccess -> {
                    loginState = 1
                    _doRegister.value = response.data
                    showLoading = false
                }

                is APIResult.APIFailure -> {
                    loginState = 2
                    showLoading = false
                    _doRegister.value = null
                }
                else -> {}
            }
        }

    }

    fun addUserData(email: String, userId:String){
        try {
            viewModelScope.launch {
                val result = userRepository.addUserData(User(userId = userId,email))
                loginState = 0
            }
        }
        catch (exception: Exception){

        }
    }
}

class RegisterViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(AuthRepository(), UserRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}