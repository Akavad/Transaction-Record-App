package com.example.dell.assignment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    ViewPager vp;
    CustomPageAdapter cpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp=findViewById(R.id.pager);
        cpa=new CustomPageAdapter(getSupportFragmentManager());
        vp.setAdapter(cpa);
        PagerTabStrip pagerTabStrip=vp.findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColor(Color.BLACK);
        pagerTabStrip.setDrawFullUnderline(true);
    }
}
