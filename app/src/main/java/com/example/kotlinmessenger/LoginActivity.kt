package com.example.kotlinmessenger

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            val email = email_et_login.text.toString()
            val password = password_et_login.text.toString()

            Log.d("Login","Attempt login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to log in: ${it.message}")
                    Toast.makeText(this,"Failed to log in: ${it.message}",Toast.LENGTH_SHORT).show()
                }
        }

        back_to_registration_et_login.setOnClickListener {
            finish()
        }
    }
}