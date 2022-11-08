package com.sabdio.workoutlog.database

import android.content.Context
import androidx.room.*
import com.sabdio.workoutlog.WorkouPlanItem
import com.sabdio.workoutlog.WorkoutPlan
import com.sabdio.workoutlog.models.ExerciseCategory
import com.sabdio.workoutlog.models.Exercises
import com.sabdio.workoutlog.models.WorkoutLogRecord

@Database(entities = arrayOf(ExerciseCategory::class, Exercises:: class, WorkoutPlan::class, WorkouPlanItem::class, WorkoutLogRecord::class), version = 5)
@TypeConverters(Converters::class)
abstract class WorkoutDb: RoomDatabase() {

    abstract fun  exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun  exerciseDao(): ExerciseDao
    abstract  fun workoutPlanDao(): WorkoutPlanDao
    abstract  fun workoutPlanItemDao(): WorkoutPlanItemDao
    abstract fun workoutLogDao(): WorkoutLogDaoRecord



    companion object{
        private var database:WorkoutDb? =null

        fun getDatabase(context: Context):WorkoutDb{
            if (database==null){
                database = Room
                    .databaseBuilder(context,WorkoutDb::class.java,"WorkoutDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as WorkoutDb
        }
    }
}

