package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    val list = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item,parent,false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = list[position]
        holder.nameTextView.text = shopItem.name
        holder.countTextView.text = shopItem.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }

    }

    override fun getItemCount(): Int = list.size

    class ShopListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val shopItem = itemView.findViewById<CardView>(R.id.shopItem)
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val countTextView = itemView.findViewById<TextView>(R.id.countTextView)
    }
}
