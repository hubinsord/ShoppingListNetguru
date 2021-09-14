package com.netguru.shoppinglistnetguru.shoppinglists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListsBinding

class ShoppingListsAdapter(
    private val shoppingLists: List<List<ShoppingItem>>,
    private val listener: ShoppingListAdapterListener,
) : RecyclerView.Adapter<ShoppingListsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemShoppingListsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListsBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvListName.text = "Shopping list"
            container.setOnClickListener { listener.onShoppingListClicked(shoppingLists[position]) }
        }
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    companion object {
        interface ShoppingListAdapterListener {
            fun onShoppingListClicked(list: List<ShoppingItem>)
        }
    }
}