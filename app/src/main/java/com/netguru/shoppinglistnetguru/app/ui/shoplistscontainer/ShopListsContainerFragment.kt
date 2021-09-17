package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.netguru.shoppinglistnetguru.databinding.FragmentListsContainerBinding

class ShopListsContainerFragment : Fragment() {

    private lateinit var binding: FragmentListsContainerBinding
    private lateinit var viewModel: ShopListsContainerViewModel
    private var adapter: ShopListsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListsContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(ShopListsContainerViewModel::class.java)
    }

    private fun initViews() {
        val adapter = ShopListsAdapter(this)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(adapter.getItemNameRes(position))
        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListsContainerFragment()
    }
}