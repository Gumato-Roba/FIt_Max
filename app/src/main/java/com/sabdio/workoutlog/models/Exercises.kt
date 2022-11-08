package com.sabdio.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Exercises")
data class Exercises(
    var description: String?,
    @PrimaryKey @SerializedName("exercise_id")var exerciseId : String,
    var image : String?,
    @SerializedName("category_id") var categoryId: String,
    @SerializedName("exercise_name")var ExerciseName: String,
)
