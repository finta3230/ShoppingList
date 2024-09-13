package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

// Так как Domain не имеет связи с остальной частью проекта, то для получения данных извне необходимо
// создать интерфейс который будет реализовывать необходимые действия с БД.

interface ShopListRepository {

    fun addShopItem(item: ShopItem)

    fun getShopList() : LiveData<List<ShopItem>>

    fun getShopItem(id : Int) : ShopItem?

    fun removeShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

}