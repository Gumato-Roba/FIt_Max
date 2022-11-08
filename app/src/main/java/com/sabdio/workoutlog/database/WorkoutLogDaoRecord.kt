package com.sabdio.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabdio.workoutlog.WorkoutPlan
import com.sabdio.workoutlog.models.WorkoutLogRecord
import java.util.*

@Dao
interface WorkoutLogDaoRecord {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLog: WorkoutLogRecord)

    @Query("SELECT * FROM workoutlogrecord WHERE userId =:userId AND date >=:currentDate")
    fun getWorkoutLogsById(userId: String,currentDate: String) :LiveData<List<WorkoutLogRecord>>
}