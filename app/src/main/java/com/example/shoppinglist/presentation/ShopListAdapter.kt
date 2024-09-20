package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder> (ShopItemDiffCallback()) {

    companion object {
        const val IS_ACTIVE = 1
        const val IS_NON_ACTIVE = 0
        const val MAX_POOL_SIZE = 15
    }

    var onShopItemClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
/*
1ый вариант:
    Создаём экземпляр Diff util чтобы при присвоении нового значения в списке происходило сравнение элементов.
    Экземпляр DiffUtil.calculateDiff вычисляет все изменения и хранит в переменной diffResult
    при помощи callback (правила сравнеения мы прописали в классе ShopListDiffCallback).
    Метод dispatchUpdatesTo обновляет наш список в адаптере
 2ой вариант:
    Создаём класс item Diff Callback который работает с элементами а не списками. А также вычисления
    производятся на отдельном потоке.
    Также меняем наследование адаптера от ListAdapter где в конструктор передаём класс Diff.ItemCallback.
    теперь метод getSize не нужен, а также onRecycledView.
    Обновление списка происходит через метод submitList()

 */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.shop_item, parent, false)
        return ShopItemViewHolder(view)
    }

    // Вызываем метод invoke, чтобы доставить ? перед вызовом
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
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
        if (getItem(position).enabled) IS_ACTIVE else IS_NON_ACTIVE

}
