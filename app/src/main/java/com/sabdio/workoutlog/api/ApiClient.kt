package com.sabdio.workoutlog.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sabdio.workoutlog.viewModel.WorkoutLog
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(WorkoutLog.appContext))
        .build()
    var retrofit = Retrofit.Builder()
        .baseUrl("http://192.81.215.35")
        .addConverterFactory(GsonConverterFactory.create()).client(client)
        .build()

    fun <T> buildApiClient(apiInterface: Class<T>): T{
        return retrofit.create(apiInterface)
    }
}