package com.roksanagulewska.pawchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    val sTAG = "SignUpActivity"
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val btnOk = findViewById<Button>(R.id.btn_ok)

        btnOk.setOnClickListener{
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            registerUser(email, password)
            if(auth.currentUser != null){
                //got to next activity
            }
        }
    }

    private fun registerUser(email: String, password : String){
        if(email.isEmpty() || password.isEmpty()) {
            val emptyCredentialsMessage = "Please enter email and password."
            Log.e(sTAG, "Email or password wasn't entered.")
            Toast.makeText(this, emptyCredentialsMessage, Toast.LENGTH_LONG).show()
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, "Registered successfully.", Toast.LENGTH_LONG).show()
                }
            } catch(e: java.lang.Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}