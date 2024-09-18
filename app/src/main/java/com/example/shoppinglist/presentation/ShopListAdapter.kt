package com.example.shoppinglist.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    companion object{
        private const val IS_ACTIVE = 1
        private const val IS_NON_ACTIVE = 0
    }

    var count : Int = 0

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder count = ${++count}")
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.shop_item, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) {
            "Active"
        } else {
            "Non active"
        }
        holder.itemView.setOnLongClickListener {
//            !shopItem.enabled
            true
        }
        if (getItemViewType(position) == IS_NON_ACTIVE) {
            holder.nameTextView.text = "${shopItem.name} + $status"
            holder.countTextView.text = shopItem.count.toString()
            holder.shopItemLinearLayout.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.nonActive_item_bg))
        } else {
            holder.nameTextView.text = "${shopItem.name} + $status"
            holder.countTextView.text = shopItem.count.toString()
            holder.shopItemLinearLayout.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.active_item_bg))
        }

    }

    override fun getItemViewType(position: Int): Int = if(shopList[position].enabled) IS_ACTIVE else IS_NON_ACTIVE

    override fun getItemCount(): Int = shopList.size

    class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopItemLinearLayout = itemView.findViewById<LinearLayout>(R.id.shopItemLinearLayout)
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val countTextView = itemView.findViewById<TextView>(R.id.countTextView)
    }
}
