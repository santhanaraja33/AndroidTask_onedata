package com.example.androidtask_onedata.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.androidtask_onedata.screens.BluetoothFragment;
import com.example.androidtask_onedata.screens.ListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new BluetoothFragment();
        } else if (position == 1) {
            fragment = new ListFragment();
        }

        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Bluetooth Devices";
        } else if (position == 1) {
            title = "List of Movies";
        }
        return title;
    }

}