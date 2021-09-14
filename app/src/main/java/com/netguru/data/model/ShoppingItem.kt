package com.netguru.data.model

data class ShoppingItem (
    val name: String,
    var quantity: Int,
    var isItemChecked: Boolean = false
        )