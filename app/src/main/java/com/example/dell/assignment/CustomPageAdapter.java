package com.example.dell.assignment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Dell on 04-02-2018.
 */

public class CustomPageAdapter extends FragmentStatePagerAdapter {
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=new Fragment();
        switch (position){
            case 0:
                Log.i("fragment","zero");
                f=new TransactionEntryFragment();
                break;
            case 1:
                Log.i("fragment","one");
                f=new DailyTransactionFragment();
                break;
            case 2:
                Log.i("fragment","two");
                f=new MonthlyTransactionFragment();
                break;
            case 3:
                Log.i("fragment","three");
                f=new FilterSearchTransactionFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "ENTRY";
            case 1:return "DAILY";
            case 2:return "MONTHLY";
            case 3:return "FILTER SEARCH";
            default:return null;
        }
    }
}
