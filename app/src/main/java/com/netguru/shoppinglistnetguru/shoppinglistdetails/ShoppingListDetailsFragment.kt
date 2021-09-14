package com.netguru.shoppinglistnetguru.shoppinglistdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.databinding.FragmentShoppingListDetailsBinding
import com.netguru.shoppinglistnetguru.shoppinglists.ShoppingListsAdapter
import com.netguru.shoppinglistnetguru.shoppinglists.ShoppingListsFragment

class ShoppingListDetailsFragment(
    private var items: List<ShoppingItem>
) : Fragment(){

    private lateinit var binding: FragmentShoppingListDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShoppingListDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.adapter = ShoppingListDetailsAdapter(items)
        binding.rvItems.layoutManager = LinearLayoutManager(view.context)
    }

    companion object {

        @JvmStatic
        fun newInstance(items: List<ShoppingItem>) = ShoppingListDetailsFragment(items)
    }
}
