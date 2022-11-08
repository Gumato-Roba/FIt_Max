package com.sabdio.workoutlog.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("first_name")var firstName: String,
    @SerializedName("last_name")var lastName: String,
    @SerializedName("user_id") var userId: String,
    var email: String,
)
