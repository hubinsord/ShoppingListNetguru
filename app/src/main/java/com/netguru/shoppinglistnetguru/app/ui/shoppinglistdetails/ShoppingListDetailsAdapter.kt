package com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListDetailBinding

class ShoppingListDetailsAdapter(
    var shoppingList: ShoppingList
): RecyclerView.Adapter<ShoppingListDetailsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemShoppingListDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListDetailBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvItemName.text = shoppingList.items[position].name
            tvItemQuantity.text = shoppingList.items[position].quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.items.size
    }

}