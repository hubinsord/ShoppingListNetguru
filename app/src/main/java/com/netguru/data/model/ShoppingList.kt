package com.netguru.data.model

data class ShoppingList(
    val name: String,
    var items: MutableList<ShoppingItem>
) {
}