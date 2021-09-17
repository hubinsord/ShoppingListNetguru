package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.netguru.shoppinglistnetguru.R
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.archived.ArchivedShopListsFragment
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active.ActiveShopListsFragment
import com.netguru.shoppinglistnetguru.databinding.FragmentListsContainerBinding


class ShopListsContainerFragment : Fragment() {

    private lateinit var binding: FragmentListsContainerBinding
    private lateinit var viewModel: ShopListsContainerViewModel

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
        val fragmentList = arrayListOf(
            ActiveShopListsFragment.newInstance(),
            ArchivedShopListsFragment.newInstance()
        )
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity(), fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tv_tab_list_active)
                1 -> tab.text = getString(R.string.tv_tab_list_archived)
            }
        }.attach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListsContainerFragment()
    }
}
