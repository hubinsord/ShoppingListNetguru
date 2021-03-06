package com.netguru.shoppinglist.app.ui.shoplistscontainer.active

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.netguru.shoppinglist.app.data.model.ShoppingItem
import com.netguru.shoppinglist.app.data.model.ShoppingList
import com.netguru.shoppinglist.R
import com.netguru.shoppinglist.databinding.FragmentShoppingListsBinding
import java.lang.ClassCastException
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActiveShopListsFragment : Fragment() {
    private lateinit var binding: FragmentShoppingListsBinding
    private lateinit var listener: ActiveShopListsAdapter.Companion.ShoppingListAdapterListener
    private val viewModel: ActiveShopListsViewModel by viewModels()
    private lateinit var adapter: ActiveShopListsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ActiveShopListsAdapter.Companion.ShoppingListAdapterListener
        } catch (castException: ClassCastException) {
            throw NotImplementedError("class cast fail")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        binding = FragmentShoppingListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        initObservers()
        initViews()
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllShoppingLists(false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.shoppingListsLiveData.observe(viewLifecycleOwner, { shoppingLists ->
                adapter.shoppingLists = shoppingLists
                adapter.notifyDataSetChanged()
        })
    }

    private fun initViews() {
        adapter = ActiveShopListsAdapter(mutableListOf(), listener)
        binding.rvShoppingLists.adapter = adapter
        binding.rvShoppingLists.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShoppingLists.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun initListeners() {
        binding.fabAddNewList.setOnClickListener { fabAddNewListClicked() }
    }

    private fun fabAddNewListClicked() {
        showListNameDialog()
    }

    @SuppressLint("InflateParams")
    private fun showListNameDialog() {
        val inputView = layoutInflater.inflate(R.layout.view_dialog_list_name_input, null)
        val dialog: AlertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_add_list_title))
            .setMessage(R.string.dialog_add_list_message)
            .setView(inputView)
            .setPositiveButton(R.string.dialog_positive_add) { _, _ ->
                val listName = inputView.findViewById<EditText>(R.id.et_input).text.toString()
                if (listName.isNotBlank()) {
                    addList(listName)
                } else {
                    showMessage(R.string.toast_list_name_empty)
                }
            }
            .setNegativeButton(R.string.dialog_negative_cancel) { _, _ -> }
            .create()
        dialog.show()
    }

    private fun addList(listName: String) {
        val products = mutableListOf<ShoppingItem>()
        val shoppingList = ShoppingList(listName, products)
        TransitionManager.beginDelayedTransition(binding.clContainer, AutoTransition())
        viewModel.addNewShoppingList(shoppingList)
        showMessage(R.string.toast_list_added)
    }

    private fun showMessage(messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ActiveShopListsFragment()
    }
}