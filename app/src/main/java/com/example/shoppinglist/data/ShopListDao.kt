package com.example.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.domain.ShopItem

@Dao
interface ShopListDao {

    @Insert
    fun addShopItem(shopItem: ShopItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun editShopItem(shopItem: ShopItem)

    @Query("SELECT * FROM shop_items WHERE id = :id")
    fun getShopItem(id : Int) : ShopItem

    @Query("SELECT * FROM shop_items")
    fun getShopList() : List<ShopItem>

    @Delete
    fun removeShopItem(shopItem: ShopItem)
}