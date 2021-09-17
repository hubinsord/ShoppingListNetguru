package com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.databinding.ItemShoppingListDetailBinding

class ShoppingListDetailsAdapter(
    var shoppingList: ShoppingList,
    private val listener: ShoppingListDetailAdapterListener
): RecyclerView.Adapter<ShoppingListDetailsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemShoppingListDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppingListDetailBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = shoppingList.items[position]
        holder.binding.apply {
            tvItemName.text = shoppingList.items[position].name
            tvItemQuantity.text = shoppingList.items[position].quantity.toString()
            if (product.isItemChecked) {
                ivCheck.setImageResource(R.drawable.check_done)
                vCrossed.visibility = View.VISIBLE
            } else {
                ivCheck.setImageResource(R.drawable.check_not_done)
                vCrossed.visibility = View.GONE
            }
            container.setOnClickListener {
                product.isItemChecked = !product.isItemChecked
                notifyItemChanged(position)
                listener.onProductCheckedChanged(product, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.items.size
    }

    companion object{
        interface ShoppingListDetailAdapterListener{
            fun onProductCheckedChanged(product: ShoppingItem, position: Int)
        }
    }

}