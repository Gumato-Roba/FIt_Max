package com.sabdio.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import com.sabdio.workoutlog.models.RegisterRequest
import com.sabdio.workoutlog.models.RegisterResponse
import com.sabdio.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLivedata = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    var registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()


    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                loginResponseLivedata.postValue(response.body())

            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.makeUserRequest(registerRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            } else {
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
}


                                                                                                                                                                                                                                                                                                                                                                                       //var registerResponseLiveData=MutableLiveData<RegisterResponse>()
