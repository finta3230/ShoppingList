package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

//Вся информация в ShopItem.kt

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList() : LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}