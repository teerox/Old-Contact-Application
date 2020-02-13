package com.example.contactapp.userInterface

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.R
import kotlinx.android.synthetic.main.activity_signin.*


class SignInActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val sharedPreferences:SharedPreferences = getSharedPreferences( "loginref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if(sharedPreferences.contains("username")) {
            login()
        }

        loginButton.setOnClickListener {
            val signIngIn = signIn.text.toString()
            val signPassword = sigInPassword.text.toString()
            if (signIngIn == "Anthony" && signPassword == "tony") {

                if (logincheck.isChecked) {
                    editor.putBoolean("savelogin", true)
                    editor.putString("username", signIngIn)
                    editor.putString("password", signPassword)
                    editor.apply()
                }

                login()
            }else{
                Toast.makeText(this,"wrong username or password",Toast.LENGTH_LONG).show()
            }
        }

    }
    override fun onBackPressed() {
        backPress()
    }

    fun login() {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

private fun backPress(){
    val dialogue = AlertDialog.Builder(this,
        R.style.AlertDialogCustom
    )
    dialogue.setTitle("Close Application")
    dialogue.setMessage("Do you want to close the Application")
    dialogue.setPositiveButton("Yes") { _, _ ->
        super.onBackPressed()
        finish()
    }
    dialogue.setNegativeButton("No") { _, _ ->
        super.onBackPressed()
    }
    //show the dialogue
    val alert = dialogue.create()
    alert.setCanceledOnTouchOutside(false)
    alert.show()
}
}



