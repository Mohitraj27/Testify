package com.example.exam_portal.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_portal.R
import com.example.exam_portal.adapters.QuizAdapter
import com.example.exam_portal.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

//import com.example.exam_portal.adapters.QuizAdapter
//import com.example.exam_portal.models.Quiz

class MainActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
   lateinit var adapter: QuizAdapter
  private  var quizlist= mutableListOf<Quiz>()

    lateinit var firestore : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // dummy data added for testing purpose
        populateDummyData()
        setUpViews()
    }

    private fun populateDummyData() {
      quizlist.add(Quiz("12-10-2021","12-10-1023"))
        quizlist.add(Quiz("13-10-2021","13-10-1023"))
        quizlist.add(Quiz("14-10-2021","14-10-1023"))
        quizlist.add(Quiz("15-10-2021","15-10-1023"))
        quizlist.add(Quiz("16-10-2021","16-10-1023"))
        quizlist.add(Quiz("17-10-2021","17-10-1023"))
        quizlist.add(Quiz("18-10-2021","18-10-1023"))
        quizlist.add(Quiz("12-10-2021","12-10-1023"))
        quizlist.add(Quiz("13-10-2021","13-10-1023"))
        quizlist.add(Quiz("14-10-2021","14-10-1023"))
        quizlist.add(Quiz("15-10-2021","15-10-1023"))
        quizlist.add(Quiz("16-10-2021","16-10-1023"))
        quizlist.add(Quiz("17-10-2021","17-10-1023"))
        quizlist.add(Quiz("18-10-2021","18-10-1023"))
        quizlist.add(Quiz("12-10-2021","12-10-1023"))
        quizlist.add(Quiz("13-10-2021","13-10-1023"))
        quizlist.add(Quiz("14-10-2021","14-10-1023"))
        quizlist.add(Quiz("15-10-2021","15-10-1023"))
        quizlist.add(Quiz("16-10-2021","16-10-1023"))
        quizlist.add(Quiz("17-10-2021","17-10-1023"))
        quizlist.add(Quiz("18-10-2021","18-10-1023"))


    }

    fun setUpViews(){

        setUpFirestore()
        setUpDrawerLayout()
       setUpRecyclerView()
    }

    private fun setUpFirestore() {
     firestore = FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference =firestore.collection("quizzes")
        collectionReference.addSnapshotListener{ value,error->
            if(value==null || error !=null){
                Toast.makeText(this,"Error fetching data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizlist.clear()
            quizlist.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView() {
        adapter= QuizAdapter(this,quizlist)


        val quizRecyclerView :RecyclerView = findViewById(R.id.quizRecyclerView)
        quizRecyclerView.layoutManager = GridLayoutManager(this,2)
       quizRecyclerView.adapter=adapter //set adapter for recyclerView
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.appBar))
          val drawerLayout :DrawerLayout=findViewById(R.id.main_drawer)
        actionBarDrawerToggle =ActionBarDrawerToggle(this,drawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}