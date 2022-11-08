package com.sabdio.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "ExerciseCategory" )
data class ExerciseCategory(
    @PrimaryKey  @SerializedName("category_id") var categoryId:String,
    @SerializedName("category_name") var categoryName:String,
)
