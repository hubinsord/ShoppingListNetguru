package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListsBinding

class ActiveShopListsAdapter(
    var shoppingLists: MutableList<ShoppingList>,
    private val listener: ShoppingListAdapterListener,
) : RecyclerView.Adapter<ActiveShopListsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemShoppingListsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListsBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvListName.text = shoppingLists[position].name
            container.setOnClickListener {
                listener.onShoppingListClicked(shoppingLists[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    companion object {
        interface ShoppingListAdapterListener {
            fun onShoppingListClicked(shoppingList: ShoppingList)
        }
    }
}