package com.sabdio.workoutlog.api

import com.sabdio.workoutlog.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
      fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

//    exercise categories
    @GET ("/exercise-categories")
    suspend fun fetchExerciseCategories(@Header("Authorization")accessToken: String): Response<List<ExerciseCategory>>

    @GET("/exercises")
    suspend fun fetchExercises(@Header("Authorization")accessToken:String): Response<List<Exercises>>
}