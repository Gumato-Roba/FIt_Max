package com.sabdio.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sabdio.workoutlog.models.ExerciseCategory
import com.sabdio.workoutlog.models.Exercises

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercises)
//    @Query"(SELECT)"
    @Query ( "SELECT * FROM Exercises")
    fun getExercises(): LiveData<List<Exercises>>

    @Query("SELECT * FROM Exercises WHERE categoryId = :categoryId")
    fun getExercisesByCategory(categoryId: String): LiveData<List<Exercises>>

}