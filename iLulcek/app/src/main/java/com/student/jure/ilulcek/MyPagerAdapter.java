package com.student.jure.ilulcek;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


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
                NastavitveFragment frag1 = new NastavitveFragment();
                return frag1;

            case 1:
                DanesFragment frag2 = new DanesFragment();
                return frag2;

            case 2:
                ZgodovinaFragment frag3 = new ZgodovinaFragment();
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
