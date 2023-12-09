package com.example.exam_portal.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_portal.R
import com.example.exam_portal.adapters.OptionAdapter
import com.example.exam_portal.models.Question

class QuestionActivity : AppCompatActivity() {
    private lateinit var description: TextView
    private lateinit var optionlist: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        // Initialize views
        description = findViewById(R.id.description)
        optionlist = findViewById(R.id.optionList)
        bindViews()
    }

    private fun bindViews() {
       val question= Question(
       "Which among these is OOPS Langugae?",
           "HTML",
           "XML",
           "CSS",
           "JAVA"
       )


        description.text=question.description
        val optionAdapter =OptionAdapter(this,question)
        // Set layout manager, adapter, and fixed size for RecyclerView
        optionlist.layoutManager =LinearLayoutManager(this)
        optionlist.adapter =optionAdapter
        optionlist.setHasFixedSize(true)

    }

}