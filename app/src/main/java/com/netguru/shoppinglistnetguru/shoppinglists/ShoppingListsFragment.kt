package com.netguru.shoppinglistnetguru.shoppinglists

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.databinding.FragmentShoppingListsBinding
import java.lang.ClassCastException


class ShoppingListsFragment : Fragment(){
    private lateinit var binding: FragmentShoppingListsBinding
    private lateinit var listener: ShoppingListsAdapter.Companion.ShoppingListAdapterListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ShoppingListsAdapter.Companion.ShoppingListAdapterListener
        } catch (castException: ClassCastException){
            throw NotImplementedError("class cast fail")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShoppingListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var shoppingLists = arrayListOf<List<ShoppingItem>>(
            arrayListOf(ShoppingItem("water", 5), ShoppingItem("bread", 2)),
            arrayListOf(ShoppingItem("water", 4), ShoppingItem("bread", 1))
        )
        val adapter = ShoppingListsAdapter(shoppingLists, listener)
        binding.rvShoppingLists.adapter = adapter
        binding.rvShoppingLists.layoutManager = LinearLayoutManager(view.context)
    }

    companion object {
        fun newInstance() = ShoppingListsFragment()
    }

}