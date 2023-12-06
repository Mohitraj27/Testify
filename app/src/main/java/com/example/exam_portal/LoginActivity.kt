package com.example.exam_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth =FirebaseAuth.getInstance()

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            login()
        }

        val btnSignUP: TextView =findViewById(R.id.btnSignUP)
        btnSignUP.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
               finish()
        }
    }

    private  fun login(){

        val etEmailAddress: EditText = findViewById(R.id.etloginEmailAddress)
        val etPassword: EditText = findViewById(R.id.etloginPassword)
        val email=etEmailAddress.text.toString()
        val password=etPassword.text.toString()

        if(email.isBlank() || password.isBlank() ){
            Toast.makeText(this,"Email and Password can't be empty.",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()

            }
        }
    }
}