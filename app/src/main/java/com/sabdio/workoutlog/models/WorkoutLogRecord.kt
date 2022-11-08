package com.sabdio.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class WorkoutLogRecord(
    @PrimaryKey var workoutLogId:String,
    var date:String,
    var exerciseId:String,
    var set:Int,
    var weight:Int?,
    var reps:Int,
    var workoutPlanItemId:String,
    var userId:String

    )
