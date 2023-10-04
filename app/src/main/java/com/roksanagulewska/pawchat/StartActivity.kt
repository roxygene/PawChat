package com.roksanagulewska.pawchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btnSignIn = findViewById<Button>(R.id.btn_sign_in)
        val btnSignUp = findViewById<Button>(R.id.btn_sign_up)
        val btnGoogle = findViewById<Button>(R.id.btn_google)

        btnSignIn.setOnClickListener{
            Intent(this, SignInActivity::class.java).also {
                startActivity(it)
            }
        }

        btnSignUp.setOnClickListener{
            Intent(this, SignUpActivity::class.java).also {
                startActivity(it)
            }
        }

        btnGoogle.setOnClickListener{
           
        }

    }

}