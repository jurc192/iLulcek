package com.student.jure.ilulcek.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.student.jure.ilulcek.Fragments.TodayFragment;
import com.student.jure.ilulcek.Fragments.SettingsFragment;
import com.student.jure.ilulcek.Fragments.HistoryFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    static final int NUM_TABS = 3;


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SettingsFragment frag1 = new SettingsFragment();
                return frag1;

            case 1:
                TodayFragment frag2 = new TodayFragment();
                return frag2;

            case 2:
                HistoryFragment frag3 = new HistoryFragment();
                return frag3;

            default:
                Log.d("ERR", "FragmentGetItem -> incorrect position!");
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // Kako bi se dalo to lepše uredit?
        switch (position) {
            case 0: return "NASTAVITVE";
            case 1: return "DANES";
            case 2: return "ZGODOVINA";
            default: return "ERROR @getPageTitle - invalid position";
        }

    }

}
