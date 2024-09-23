package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    fun getShopItem(id : Int) : ShopItem? = getShopItemUseCase.getShopItem(id)

    fun addShopItem(inputName : String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem((shopItem))
        }
    }

    fun editShopItem(inputName : String?, inputCount : String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if (validateInput(name, count)) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem((shopItem))
        }
    }

    private fun parseName(inputName: String?) : String = inputName?.trim() ?: ""

    private fun parseCount(inputCount: String?) : Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e : Exception){
            0
        }
    }

    private fun validateInput(name : String,count : Int ) : Boolean {
        var result = true
        if (name.isBlank()) {
//            TODO: show error input name
            result = false
        }
        if (count <= 0 ){
//            TODO: show error input count
            result = false
        }
        return result
    }
}