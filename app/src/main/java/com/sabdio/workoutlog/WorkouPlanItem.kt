package com.sabdio.workoutlog

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutPlanItem", indices = [Index(value = ["workoutPlanId","day"], unique = true)])
data class WorkouPlanItem(
    @PrimaryKey var workoutPlanItemId:String,
    var workoutPlanId:String,
    var day:Int,
    var exerciseId: List<String>
)
