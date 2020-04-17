package com.example.lab6.ui.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.R
import com.example.lab6.data.Park

class SearchRecyclerAdapter (val context: Context, val parkList: List<Park>, val itemListener: ParkItemListener) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.titleTextView)
        val stateText = itemView.findViewById<TextView>(R.id.stateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.park_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = parkList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curPark = parkList[position]

        holder.titleText.text = curPark.name
        holder.stateText.text = curPark.states

        holder.itemView.setOnClickListener {
            itemListener.onParkItemClick(curPark)
        }
    }

    interface ParkItemListener {
        fun onParkItemClick(park: Park)
    }
}