package com.sabdio.workoutlog.Ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.Utils.Constants
import com.sabdio.workoutlog.databinding.ActivityHomeBinding
import com.sabdio.workoutlog.viewmodel.ExerciseViewModel


class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
//    lateinit var tvLoout: TextView
    val exerciseViewModel: ExerciseViewModel by viewModels()
    lateinit var token:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS_FILE, MODE_PRIVATE)
        token = sharedPrefs.getString(Constants.ACCESS_TOKEN, "").toString()
        exerciseViewModel.fetchDbExercises()
        exerciseViewModel.fetchDbCategories()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()

    }

//        tvLoout = findViewById(R.id.tvLogout)
//        tvLoout.setOnClickListener {
//            val editor = sharedPrefs.edit()
//            editor.putString("ACCESS_TOKEN", "")
//            editor.putString("USER_ID", "")
//            editor.putString("PROFILE_ID", "")
//            editor.apply()
//            startActivity(Intent(this, LoginActivity::class.java))
//            logOutRequest()
//        }
//        castView()


    override fun onResume() {
        super.onResume()

            exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
                if(exerciseCategories.isEmpty()){
                    exerciseViewModel.fetchExerciseCategories(token)
                }
        })
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExercises(token)
            }
        } )
        exerciseViewModel.errorLiveData.observe(this, Observer { erroMsg->
            Toast.makeText(this,erroMsg,Toast.LENGTH_LONG).show()
        })
    }

        fun setupBottomNav() {
            binding.bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.plan -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fcvHome, PlanFragment())
                            .commit()
                        true
                    }
                    R.id.track -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fcvHome, TrackFragment())
                            .commit()
                        true
                    }
                    R.id.profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fcvHome, ProfileFragment()).commit()
                        true
                    }
                    else -> false
                }
            }
        }

    }

