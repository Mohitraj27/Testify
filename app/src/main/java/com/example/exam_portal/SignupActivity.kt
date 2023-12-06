package com.example.exam_portal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth=FirebaseAuth.getInstance()
        val btnsignup: Button = findViewById(R.id.btnsignup)


        btnsignup.setOnClickListener{
            signUpUser()
        }

        val btnSignUP: TextView =findViewById(R.id.btnSignUP)
        btnSignUP.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

   private fun signUpUser(){
       val etEmailAddress: EditText = findViewById(R.id.etEmailAddress)
       val etPassword: EditText = findViewById(R.id.etPassword)
       val etConfirmPassword: EditText = findViewById(R.id.etConfirmPassword)

       val email : String= etEmailAddress.text.toString()
       val password : String=etPassword.text.toString()
       val confirmPassword : String=etConfirmPassword.text.toString()

       if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
           Toast.makeText(this,"Email and Password can't be empty.",Toast.LENGTH_SHORT).show()
           return
       }
       if(password != confirmPassword){
           Toast.makeText(this,"Password Mismatch",Toast.LENGTH_SHORT).show()
           return
       }

       firebaseAuth.createUserWithEmailAndPassword(email,password)
           .addOnCompleteListener(this){
               if(it.isSuccessful){
             Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show()
                   val intent = Intent(this,MainActivity::class.java)
                   startActivity(intent)
                   finish()
               }
               else{
                   Toast.makeText(this,"Error creating user.",Toast.LENGTH_SHORT).show()
               }
           }
   }
}

