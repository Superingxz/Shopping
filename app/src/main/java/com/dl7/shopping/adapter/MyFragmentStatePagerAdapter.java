package com.dl7.shopping.adapter;

/**
 * Created by MC.Zeng on 2017-06-27.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dl7.shopping.module.fragment.bottomshopping.BottomShoppingFragment;
import com.dl7.shopping.module.fragment.hometop.HomeTopFragment;
import com.dl7.shopping.module.fragment.phonecart.PhoneCartFragment;
import com.dl7.shopping.module.fragment.topshopping.TopShoppingFragment;
import com.dl7.shopping.module.fragment.water.WaterFragment;


public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTilte;

    public MyFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm);
        this.tabTilte = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTopFragment();
            case 1:
                return new WaterFragment();
            case 2:
                return new PhoneCartFragment();
            case 3:
                return new TopShoppingFragment();
            case 4:
                return new BottomShoppingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTilte.length;
    }
}
