package com.sabdio.workoutlog.repository

import androidx.lifecycle.LiveData
import com.sabdio.workoutlog.WorkouPlanItem
import com.sabdio.workoutlog.WorkoutPlan
import com.sabdio.workoutlog.database.WorkoutDb
import com.sabdio.workoutlog.viewModel.WorkoutLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WorkoutPlanRepository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao = database.workoutPlanDao()
    val workoutPlanItemDao = database.workoutPlanItemDao()
//    val  workoutPlanId = database

    suspend fun saveWorkoutPlan(workoutPlan: WorkoutPlan) {
        withContext(Dispatchers.IO) {
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }
    suspend fun saveWorkoutPlanItem (workoutPlanItem: WorkouPlanItem){
        withContext(Dispatchers.IO){
            workoutPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }
    fun getWorkoutPlanByUserId(userId: String): LiveData<WorkoutPlan> {
        return workoutPlanDao.getWorkoutPlanByUserId(userId)
    }
//    fun createWorkoutPlanItem(dayNumber:Int,workoutPlanId:String){
//        val workoutPlanItem=WorkouPlanItem(workoutPlanItemId = UUID.randomUUID().toString(),workoutPlanId=workoutPlanId,
//            day = dayNumber, exerciseId = selectedExercisesIds)
//        viewModelScope.launch {
//            WorkoutPlanRepository.saveWorkoutPlanItem(workoutPlanItem)
//        }
//
//    }

}




