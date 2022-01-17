package com.example.countdownnotification

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val list:ArrayList<String>,val activity: MainActivity, val itemclick :(Int) -> Unit) :RecyclerView.Adapter<ListAdapter.ListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listholder,parent,false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val second = millisUntilFinished /1000
                holder.tasktimmer.setText(second.toString())
            }

            override fun onFinish() {
                 itemclick(holder.adapterPosition)
//                Toast.makeText(activity,"Finish the tast",Toast.LENGTH_LONG).show()

            }
        }
        timer.start()
        holder.tasktitle.setText(list[position].toString())





    }

    override fun getItemCount(): Int {
        return  list.size

    }
    class  ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val tasktitle = itemView.findViewById<TextView>(R.id.title)
        val tasktimmer = itemView.findViewById<TextView>(R.id.timmer)
    }
}