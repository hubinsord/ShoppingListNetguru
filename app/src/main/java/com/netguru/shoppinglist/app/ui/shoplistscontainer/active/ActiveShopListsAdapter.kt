package com.netguru.shoppinglist.app.ui.shoplistscontainer.active

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.shoppinglist.R
import com.netguru.shoppinglist.app.data.model.ShoppingList
import com.netguru.shoppinglist.databinding.ItemShoppingListsBinding

class ActiveShopListsAdapter(
    var shoppingLists: MutableList<ShoppingList>,
    private val listener: ShoppingListAdapterListener,
) : RecyclerView.Adapter<ActiveShopListsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListsBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = shoppingLists[position]
        holder.binding.apply {
            tvListName.text = list.name
            val products = list.items
            val checkedProducts = products.filter { it.isItemChecked }.size
            tvItemCounter.text = this.root.context.getString(R.string.item_counter, checkedProducts, products.size)
        }
    }

    override fun getItemCount(): Int {
        return shoppingLists.size
    }

    inner class ItemViewHolder(val binding: ItemShoppingListsBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.root.setOnClickListener { onClick(it) }
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = shoppingLists[position]
                listener.onShoppingListClicked(item)
            }
        }
    }

    companion object {
        interface ShoppingListAdapterListener {
            fun onShoppingListClicked(shoppingList: ShoppingList)
        }
    }
}