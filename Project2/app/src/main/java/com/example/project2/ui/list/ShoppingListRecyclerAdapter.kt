package com.example.project2.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.R
import com.example.project2.data.database.ShoppingList


class ShoppingListRecyclerAdapter (val context: Context, var shoppingList: List<ShoppingList>, val itemListener: ShoppingListItemListener) : RecyclerView.Adapter<ShoppingListRecyclerAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelText = itemView.findViewById<TextView>(R.id.labelTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = shoppingList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curItem = shoppingList[position]

        holder.labelText.text = curItem.label

        holder.itemView.setOnClickListener {
            itemListener.onShoppingListItemClick(curItem)
            val pop= PopupMenu(this.context, it)
            pop.inflate(R.menu.context_menu)

            pop.setOnMenuItemClickListener {item->

                when(item.itemId) {
                    R.id.visit->{
                        itemListener.visit(curItem)
                    }

                    R.id.edit->{
                        itemListener.edit(curItem)
                    }

                    R.id.delete->{
                        itemListener.delete(curItem)
                    }
                }
                true
            }
            pop.show()
            true
        }
    }

    interface ShoppingListItemListener {
        fun onShoppingListItemClick(shoppingListItem: ShoppingList)
        fun delete(shoppingListItem: ShoppingList)
        fun edit(shoppingListItem: ShoppingList)
        fun visit(shoppingListItem: ShoppingList)
    }
}