package com.example.flexiblerecycler.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flexiblerecycler.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    private val _itemFlow = MutableStateFlow(emptyList<Item>())
    val itemFlow get() = _itemFlow.asStateFlow()

    fun addNewItem(id: Int, text: String, buttonText: String) {
        viewModelScope.launch {
            _itemFlow.value = _itemFlow.value.toMutableList().apply {
                add(Item(id, text, buttonText))
            }
        }
    }

    fun deleteLastItem() {

    }
}