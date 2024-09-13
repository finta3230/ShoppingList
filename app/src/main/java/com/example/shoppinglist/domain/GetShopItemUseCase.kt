package com.example.shoppinglist.domain

//Вся информация в ShopItem.kt


class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(id : Int) : ShopItem? {
        return shopListRepository.getShopItem(id)
    }
}