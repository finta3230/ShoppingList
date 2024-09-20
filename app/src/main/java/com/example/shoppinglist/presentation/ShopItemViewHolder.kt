package com.example.shoppinglist.presentation

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val shopItemLinearLayout = itemView.findViewById<LinearLayout>(R.id.shopItemLinearLayout)
    val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    val countTextView = itemView.findViewById<TextView>(R.id.countTextView)
}