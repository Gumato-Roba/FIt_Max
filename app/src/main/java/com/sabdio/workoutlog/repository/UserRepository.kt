package com.sabdio.workoutlog.repository

import com.sabdio.workoutlog.api.ApiClient
import com.sabdio.workoutlog.api.ApiInterface
import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import com.sabdio.workoutlog.models.RegisterRequest
import com.sabdio.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse>
     = withContext(Dispatchers.IO){
         val response = apiClient.loginUser(loginRequest)
         return@withContext response
     }
    suspend fun makeUserRequest(registerRequest: RegisterRequest)
            = withContext(Dispatchers.IO){
        val response=apiClient.registerUser(registerRequest)
        return@withContext response
    }
}