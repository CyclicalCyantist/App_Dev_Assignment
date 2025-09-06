package com.example.recycleview_simple

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>(
        listOf(
            Item("1", "Caldo Verde", ItemCategories.SIDE, "Green soup"),
            Item("2", "Salisbury Steak", ItemCategories.MAIN, "Fake steak"),
            Item("3", "Pho", ItemCategories.MAIN, "Beef noodle soup")
        )
    )

    val items: LiveData<List<Item>> = _items

    fun toggleFav(item: Item) {
        viewModelScope.launch {
            val currentList = _items.value.orEmpty()
            val updatedList = currentList.map {
                if (it.id == item.id) it.copy(isFavourite = !it.isFavourite)
                else it
            }
            _items.value = updatedList
        }
    }
}