package com.example.lab8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8.data.database.Contacts

class ContactsRecyclerAdapter (val context: Context, var contactsList: List<Contacts>, val itemListener: ContactItemListener) : RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.nameTextView)
        val numberText = itemView.findViewById<TextView>(R.id.numberTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.contact_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contactsList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curContact = contactsList[position]

        holder.nameText.text = curContact.name
        holder.numberText.text = curContact.number.toString()

        holder.itemView.setOnClickListener {
            itemListener.onContactItemClick(curContact)
        }
    }

    interface ContactItemListener {
        fun onContactItemClick(contact: Contacts)
    }
}