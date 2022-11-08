package com.sabdio.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabdio.workoutlog.models.WorkoutLogRecord
import com.sabdio.workoutlog.repository.WorkoutLogRepository
import com.sabdio.workoutlog.viewModel.WorkoutLog
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class WorkoutLogRecordViewModel: ViewModel() {
    val workoutLogRepository = WorkoutLogRepository()
    lateinit var todaysRecordsLiveData: LiveData<List<WorkoutLogRecord>>

    fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        viewModelScope.launch {
            workoutLogRepository.saveWorkoutLogRecord(workoutLogRecord) }
    }
    fun getTodaysWorkoutLogRecords(userId:String){
        todaysRecordsLiveData = workoutLogRepository
            .getTodaysWorkoutLogRecords(userId,getCurrentDate())
    }

    fun getCurrentDate(): String{
        val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH)
        return formatter.format(Date())
    }
}