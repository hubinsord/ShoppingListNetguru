package com.netguru.shoppinglistnetguru.shoppinglistdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListDetailBinding

class ShoppingListDetailsAdapter(
    private val shoppingItems: List<ShoppingItem>
): RecyclerView.Adapter<ShoppingListDetailsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemShoppingListDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListDetailBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvItemName.text = shoppingItems[position].name
        }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

}