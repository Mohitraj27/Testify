package com.example.exam_portal.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exam_portal.R
import com.example.exam_portal.models.Question

class OptionAdapter(val context:Context, val question: Question):
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options: List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
    inner class OptionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var optionView=itemView.findViewById<TextView>(R.id.quiz_option)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
       return options.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {

        holder.optionView.text=options[position]
        holder.itemView.setOnClickListener{
      // Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show()
       question.userAnswer=options[position]
            notifyDataSetChanged()
        }
        if(question.userAnswer == options[position]){
           holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

        }
    }
}