package com.example.contactapp.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.contactapp.R
import kotlinx.android.synthetic.main.activity_main_signup.*

class MainActivitySignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_signup)
    }

    fun signup(v: View){

        val signupName = sign_up_user_name.text.toString()
        val signupPassword = sign_up_password.text.toString()
        val signupEmail = sign_up_email.text.toString()
        val signupPhoneNumber = sign_up_phone_number.text.toString()

        Log.e("Signup", "$signupName $signupPassword $signupEmail $signupPhoneNumber")

    }
}
