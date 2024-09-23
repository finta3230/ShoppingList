package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveShopItemUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel(private val repository: ShopListRepositoryImpl) : ViewModel() {

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _shopList = MutableLiveData<List<ShopItem>>(emptyList())
    val shopList : LiveData<List<ShopItem>> = _shopList


    init {
        _shopList.postValue(getShopListUseCase.getShopList())
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }

    fun changeShopItemState(shopItem: ShopItem){
        editShopItemUseCase.editShopItem(shopItem.copy(enabled = !shopItem.enabled))
    }
}

class MainViewModelFactory(private val repositoryImpl: ShopListRepositoryImpl) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) return MainViewModel(repositoryImpl) as T
        return super.create(modelClass)
    }
}