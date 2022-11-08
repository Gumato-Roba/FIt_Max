package com.sabdio.workoutlog.Ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.Utils.Constants
import com.sabdio.workoutlog.api.ApiClient
import com.sabdio.workoutlog.api.ApiInterface
import com.sabdio.workoutlog.databinding.ActivityLoginBinding
import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import com.sabdio.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var  binding:ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLivedata.observe(this, Observer { LoginResponse->
            Toast.makeText(baseContext,LoginResponse?.message, Toast.LENGTH_LONG).show()
            saveLoginDetails(LoginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))

        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG)
//            Toast.makeText(baseContext,LoginResponse?.message, Toast.LENGTH_LONG).show()

        })
    }

    fun castviews(){
        binding.btnLogin.setOnClickListener{
            validateLogin()
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.tvSignup.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateLogin(){
        var error=false
        binding.tilPassword1.error= null
        binding.tilEmailaddress.error = null
        var email = binding.etEmailaddress.text.toString()

        if (email.isBlank()) {
            binding.tilEmailaddress.error = "Enter email"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmailaddress.error = "Not a valid email address"
            error=true
        }

        var password = binding.etPassword1.text.toString()
        if (password.isBlank()) {
            binding.tilPassword1.error = "Enter password"
            error = true

        }
        if(!error){
            val loginRequest = LoginRequest(email,password)
            binding.pbLogin.visibility = View.VISIBLE
            userViewModel.loginUser(loginRequest)

        }
    }

    fun saveLoginDetails(LoginResponse: LoginResponse){
        var editor = sharedPrefs.edit()
        val token = "Bearer ${LoginResponse.accesstoken}"
        editor.putString(Constants.ACCESS_TOKEN,token)
        editor.putString(Constants.USER_ID,LoginResponse.userId)
        editor.putString(Constants.PROFILE_ID,LoginResponse.profileId)
        editor.apply()

//        editor.putString(Constants.userId,loginResponse.userId)
//        editor.putString(Constants.profileId,loginResponse.profileId)
    }
}
