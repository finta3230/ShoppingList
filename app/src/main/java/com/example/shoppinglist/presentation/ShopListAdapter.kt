package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ItemDiffUtil()) {

    companion object {
        const val ACTIVE = 1
        const val NON_ACTIVE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        val colorId = when (getItemViewType(position)) {
            ACTIVE -> R.color.active_item_background
            NON_ACTIVE -> R.color.non_active_item_background
            else -> throw RuntimeException("Unknown view type")
        }

        holder.nameTextView.text = shopItem.name
        holder.countTextView.text = shopItem.count.toString()
        holder.shopItemLinearLayout.setBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, colorId)
        )
    }

    override fun getItemViewType(position: Int): Int = if (getItem(position).enabled) ACTIVE
    else NON_ACTIVE


    class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopItemLinearLayout: LinearLayout = itemView.findViewById(R.id.shopItemLinearLayout)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val countTextView: TextView = itemView.findViewById(R.id.countTextView)
    }
}