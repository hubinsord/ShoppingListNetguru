package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.archived

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListsArchivedBinding

class ArchivedShopListsAdapter(var archivedShoppingLists: MutableList<ShoppingList>): RecyclerView.Adapter<ArchivedShopListsAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(val binding: ItemShoppingListsArchivedBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListsArchivedBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvListName.text = archivedShoppingLists[position].name
            }
    }

    override fun getItemCount(): Int = archivedShoppingLists.size
}