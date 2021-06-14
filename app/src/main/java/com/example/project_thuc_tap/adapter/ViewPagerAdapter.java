package com.example.project_thuc_tap.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.project_thuc_tap.activity.TabAccount;
import com.example.project_thuc_tap.activity.TabHome;
import com.example.project_thuc_tap.activity.TabList;
import com.example.project_thuc_tap.activity.TabShopping;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TabHome();
            case 1:
                return new TabList();
            case 2:
                return new TabShopping();
            case 3:
                return new TabAccount();
            default:
                return new TabHome();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
