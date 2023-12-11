package com.example.exam_portal.activites

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_portal.R
import com.example.exam_portal.adapters.OptionAdapter
import com.example.exam_portal.models.Question
import com.example.exam_portal.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class QuestionActivity : AppCompatActivity() {
     private lateinit var description: TextView
     private lateinit var optionlist: RecyclerView
     lateinit var btnPrevious: Button
     lateinit var btnNext: Button
     lateinit var btnSubmit: Button

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String,Question>?=null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        // bindViews()
        setUpFirestore()
    }

    private fun setUpFirestore() {
       val  firestore:FirebaseFirestore= FirebaseFirestore.getInstance()

        var date = intent.getStringExtra("DATE")
        if(date!=null){
            firestore.collection("quizzes")
                .whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it!=null && !it.isEmpty){

                      quizzes=  it.toObjects(Quiz ::class.java)
                        questions = quizzes!![0].questions

                       bindViews()
                    }

                }
        }




    }


    private fun bindViews() {

        btnPrevious =findViewById(R.id.btnPrevious)
        btnNext =findViewById(R.id.btnNext)
        btnSubmit =findViewById(R.id.btnSubmit)
        description= findViewById(R.id.description)
        optionlist = findViewById(R.id.optionList)

        // to hide the three buttons when quiz started and show these buttons accordingly
        // to the questions conditions
        btnPrevious.visibility = View.GONE
        btnNext.visibility =View.GONE
        btnSubmit.visibility =View.GONE

        if(index ==1){ // when first question appears
            btnNext.visibility =View.VISIBLE

        }
        else if(index == questions!!.size){ // conditon for last questions
            btnSubmit.visibility =View.VISIBLE
            btnPrevious.visibility =View.VISIBLE
        }
        else{
            // middle questions when both previous and next buttons are visible
          btnPrevious.visibility=View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }

        val question:Question? =questions!!["question$index"]
           question?.let {

               description.text=it.description
               val optionAdapter =OptionAdapter(this,it)
               // Set layout manager, adapter, and fixed size for RecyclerView
               optionlist.layoutManager =LinearLayoutManager(this)
               optionlist.adapter =optionAdapter
               optionlist.setHasFixedSize(true)
           }



    }

}