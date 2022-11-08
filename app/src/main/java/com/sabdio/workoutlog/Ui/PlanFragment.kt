package com.sabdio.workoutlog.Ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationBarView
import com.sabdio.workoutlog.CategoryAdapter
import com.sabdio.workoutlog.ExerciseAdapter
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.Utils.Constants
import com.sabdio.workoutlog.WorkoutPlan
import com.sabdio.workoutlog.databinding.FragmentPlanBinding
import com.sabdio.workoutlog.models.ExerciseCategory
import com.sabdio.workoutlog.models.Exercises
import com.sabdio.workoutlog.viewmodel.ExerciseViewModel
import com.sabdio.workoutlog.viewmodel.WorkoutPlanViewModel
import java.util.*


class PlanFragment : Fragment() {
    val exerciseViewModel: ExerciseViewModel by viewModels()
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    var binding: FragmentPlanBinding? = null
    lateinit var workoutPlanId:String


    val bind get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setupDaySpinner()
        exerciseViewModel.fetchDbCategories()
        exerciseViewModel.fetchDbExercises()
        setupCategorySpinner()
        bind.btnAddItem.setOnClickListener { clickAddItem() }
        checkForExistingWorkoutPlan()
        bind.btnAddItem.setOnClickListener {clickAddItem()}
        bind.btnSave.setOnClickListener{clickSaveDay()}
        checkForExistingWorkoutPlan()
//        setupExerciseSpinner()

    }

    fun setupDaySpinner() {
        val days = listOf(
            "Select Day",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday",
        )
        val daysAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, days)
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spFirst.adapter = daysAdapter

    }

    fun setupCategorySpinner() {
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { category ->
            val firstCategory = ExerciseCategory("0", "Select Category")
            val displayCategories = mutableListOf(firstCategory)
            displayCategories.addAll(category)
            val categoryAdapter = CategoryAdapter(requireContext(), displayCategories)
            bind.spCategories.adapter = categoryAdapter
            bind.spCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = displayCategories.get(position)
                    var categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                    setupExerciseSpinner()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        })
    }

    fun setupExerciseSpinner() {
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises ->
            val firstCategory = Exercises("", "1", "", "0", "Select Exercise")
            val displayExercises = mutableListOf(firstCategory)
            displayExercises.addAll(exercises)
            val exerciseAdapter = ExerciseAdapter(requireContext(), displayExercises)
            bind.spExercise.adapter = exerciseAdapter
        })
    }

    fun clickAddItem() {
        var error = false
        if (bind.spFirst.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Day", Toast.LENGTH_LONG).show()
        }
        if (bind.spCategories.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Category", Toast.LENGTH_LONG).show()
        }
        if (bind.spExercise.selectedItemPosition == 0) {
            error = true
            Toast.makeText(requireContext(), "Select Exercise", Toast.LENGTH_LONG).show()
        }
        if (!error) {
            val selectedExercise = bind.spExercise.selectedItem as Exercises
//            unfinished
            workoutPlanViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)
            bind.spExercise.setSelection(0)
            bind.spCategories.setSelection(0)
        }
    }

    fun checkForExistingWorkoutPlan() {
        val prefs =
            requireContext().getSharedPreferences(Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        val userId = prefs.getString(Constants.USER_ID, "").toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)
        workoutPlanViewModel.workoutPlanLiveData.observe(this, Observer { workoutPlan ->
            if (workoutPlan == null) {
                val newWorkoutPlan = WorkoutPlan(UUID.randomUUID().toString(), userId)
                workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)
            }else{
                workoutPlanId = workoutPlan.workoutPlanId
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun getDayNumber(day:String): Int {
    val dayMap = HashMap<String, Int>()
    dayMap.put("Monday",1)
    dayMap.put("Tuesday",2)
    dayMap.put("Wednesday",3)
    dayMap.put("Thursday",4)
    dayMap.put("Friday",5)
    dayMap.put("Saturday",6)
    dayMap.put("Sunday",7)
    return dayMap.get(day) ?: -1
}

//    done

    fun clickSaveDay() {
        if (bind.spFirst.selectedItemPosition == 0) {
           Toast.makeText(requireContext(), "Select Day", Toast.LENGTH_LONG).show()
           return
       }
        val day = bind.spFirst.selectedItem.toString()
        val dayNumber = getDayNumber(day)
        if (workoutPlanViewModel.selectedExerciseIds.isEmpty()) {
            Toast.makeText(requireContext(), "Select Day", Toast.LENGTH_LONG).show()
            return
        }
        if (dayNumber !=null){
            workoutPlanViewModel.createWorkoutPlanItem(dayNumber,workoutPlanId)

    }
        bind.spFirst.setSelection(0)

 }
}
