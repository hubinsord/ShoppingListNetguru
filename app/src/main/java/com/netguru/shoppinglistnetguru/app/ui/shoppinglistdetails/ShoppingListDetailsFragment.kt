package com.netguru.shoppinglistnetguru.app.ui.shoppinglistdetails

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingItem
import com.netguru.shoppinglistnetguru.app.data.model.ShoppingList
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.databinding.FragmentShoppingListDetailsBinding

class ShoppingListDetailsFragment(
    private var shoppingList: ShoppingList
) : Fragment(){

    private lateinit var binding: FragmentShoppingListDetailsBinding
    private lateinit var viewModel: ShoppingListDetailsViewModel
    private lateinit var adapter: ShoppingListDetailsAdapter
    private lateinit var listener: ShoppingListDetailsFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as ShoppingListDetailsFragmentListener
        } catch (castException: ClassCastException) {
            throw NotImplementedError("class cast failed")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShoppingListDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateShoppingList(shoppingList)
        initObservers()
        initViews()
        initListeners()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(requireActivity().application)
            .create(ShoppingListDetailsViewModel::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.shoppingListLiveData.observe(viewLifecycleOwner, { shoppingList ->
            if(shoppingList != null){
                adapter.shoppingList = shoppingList
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initViews(){
        adapter = ShoppingListDetailsAdapter(shoppingList)
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initListeners() {
        binding.fabAddNewItem.setOnClickListener { fabAddNewItemClicked() }
        binding.btnArchiveList.setOnClickListener { btnArchiveListClicked() }
    }

    private fun fabAddNewItemClicked() {
        showListNameDialog()
    }

    private fun btnArchiveListClicked() {
       viewModel.archiveShoppingList()
        listener.onBtnArchiveListClicked()
    }

    @SuppressLint("InflateParams")
    private fun showListNameDialog() {
        val inputView = layoutInflater.inflate(R.layout.view_dialog_product_name_input, null)
        val dialog: AlertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_add_product_title))
            .setView(inputView)
            .setPositiveButton(R.string.dialog_positive_add) { _, _ ->
                val productName = inputView.findViewById<EditText>(R.id.et_name_input).text.toString()
                val productQuantity = inputView.findViewById<EditText>(R.id.et_quantity_input).text.toString()
                if (productName.isNotBlank() && productQuantity.isNotBlank()) {
                    addProduct(productName, productQuantity)
                } else {
                    showMessage(R.string.toast_product_name_empty)
                }
            }
            .setNegativeButton(R.string.dialog_negative_cancel) { _, _ -> }
            .create()
        dialog.show()
    }

    private fun addProduct(productName: String, productQuantity: String) {
        val product = ShoppingItem(productName, productQuantity.toInt())
        viewModel.addNewProduct(product)
        showMessage(R.string.toast_product_added)
    }

    private fun showMessage(messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }

    companion object {

        @JvmStatic
        fun newInstance(shoppingList: ShoppingList) = ShoppingListDetailsFragment(shoppingList)

        interface ShoppingListDetailsFragmentListener{
            fun onBtnArchiveListClicked()
        }
    }
}
