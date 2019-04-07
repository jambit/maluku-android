package com.jambit.maluku.android.maluku.android.malukuandroidapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballChat;
import com.jambit.maluku.android.maluku.android.malukuandroidapp.fragments.FoosballTable;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FoosballTable();
            case 1:
                return new FoosballChat();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Kickertische";
            case 1:
                return "Spielerliste";
            default:
                return null;
        }
    }
}
