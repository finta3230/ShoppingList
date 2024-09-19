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

    companion object {
        const val IS_ACTIVE = 1
        const val IS_NON_ACTIVE = 0
        const val MAX_POOL_SIZE = 15
    }

    var onShopItemClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null

    var count: Int = 0

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

    // Вызываем метод invoke, чтобы доставить ? перед вызовом
    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        val colorId = when (getItemViewType(position)) {
            IS_ACTIVE -> R.color.active_item_bg
            IS_NON_ACTIVE -> R.color.nonActive_item_bg
            else -> throw RuntimeException("Unknown view type")
        }
        holder.nameTextView.text = shopItem.name
        holder.countTextView.text = shopItem.count.toString()
        holder.shopItemLinearLayout.setBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, colorId)
        )

    }


    override fun getItemViewType(position: Int): Int =
        if (shopList[position].enabled) IS_ACTIVE else IS_NON_ACTIVE

    override fun getItemCount(): Int = shopList.size


    class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopItemLinearLayout = itemView.findViewById<LinearLayout>(R.id.shopItemLinearLayout)
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val countTextView = itemView.findViewById<TextView>(R.id.countTextView)
    }
}
