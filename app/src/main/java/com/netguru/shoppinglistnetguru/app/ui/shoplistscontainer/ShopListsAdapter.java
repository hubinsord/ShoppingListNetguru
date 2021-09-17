package com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.netguru.shoppinglistnetguru.R;
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.active.ActiveShopListsFragment;
import com.netguru.shoppinglistnetguru.app.ui.shoplistscontainer.archived.ArchivedShopListsFragment;

import java.util.ArrayList;

public class ShopListsAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragmentList;

    public ShopListsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragmentList = new ArrayList<>();
        fragmentList.add(ActiveShopListsFragment.Companion.newInstance());
        fragmentList.add(ArchivedShopListsFragment.Companion.newInstance());
    }

    public Integer getItemNameRes(Integer position) {
        switch (position) {
            case 0:
                return R.string.tv_tab_list_active;
            case 1:
                return R.string.tv_tab_list_archived;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}