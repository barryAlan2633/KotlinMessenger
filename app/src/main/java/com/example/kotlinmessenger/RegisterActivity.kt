package com.example.kotlinmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        register_btn_register.setOnClickListener {
            performRegister()
        }

        already_have_account_tv_register.setOnClickListener {
            Log.d("RegisterActivity","Try to show login Activity")

            //Launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        select_photo_btn_register.setOnClickListener {
            Log.d("RegisterActivity", "Try to show photo selector")

        }
    }

    private fun performRegister(){
        val email = email_et_register.text.toString()
        val password = password_et_register.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter text in email", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("RegisterActivity","Email is: " + email)
        Log.d("RegisterActivity","Password is: $password")

        //Firebase Authentication email/password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                // Log.d("Main", "Successfully created user with uid: ${it.result.user.uid}")
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()

            }
    }
}
