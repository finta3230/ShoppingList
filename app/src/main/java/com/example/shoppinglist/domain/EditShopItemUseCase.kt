package com.example.shoppinglist.domain

//Вся информация в ShopItem.kt

class EditShopItemUseCase (private val shopListRepository: ShopListRepository){

    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}