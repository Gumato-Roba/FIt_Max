package com.sabdio.workoutlog.repository

import androidx.lifecycle.LiveData
import com.sabdio.workoutlog.database.WorkoutDb
import com.sabdio.workoutlog.database.WorkoutLogDaoRecord
import com.sabdio.workoutlog.models.WorkoutLogRecord
import com.sabdio.workoutlog.viewModel.WorkoutLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WorkoutLogRepository {
    val database =WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao =database.workoutLogDao()

     suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }
    fun getTodaysWorkoutLogRecords(userId:String, currentDate: String): LiveData<List<WorkoutLogRecord>>{
        return  workoutLogRecordDao.getWorkoutLogsById(userId,currentDate)

    }

}