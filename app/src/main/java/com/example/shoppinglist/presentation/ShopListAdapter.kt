package com.example.shoppinglist.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopListAdapter : ListAdapter<ItemDiffUtil, ShopListAdapter.ShopItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}