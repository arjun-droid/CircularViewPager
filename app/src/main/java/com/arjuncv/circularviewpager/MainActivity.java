package com.arjuncv.circularviewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import circularadapter.CircularAdapter;
import circularadapter.CircularViewPagerOperator;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<String> alphabets;
    private int fakePageCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAlphabets();
        initView();
    }

    private void createAlphabets() {
        alphabets = new ArrayList<>();
        alphabets.add("Android");
        alphabets.add("Blackberry");
        alphabets.add("CAT");
        alphabets.add("Dell");
        alphabets.add("Ericsson");
        alphabets.add("Fujitsu");
        alphabets.add("Google");
    }

    private void initView() {
        viewPager = findViewById(R.id.cover_pager);
        tabLayout = findViewById(R.id.tabDots);
        viewPager.addOnPageChangeListener(this);

        CircularAdapter circularAdapter = new CircularAdapter(getSupportFragmentManager(), alphabets, fakePageCount);
        viewPager.setPageMargin(50);
        viewPager.setAdapter(circularAdapter);
        viewPager.setOffscreenPageLimit(alphabets.size());

        CircularViewPagerOperator circularViewPagerOperator = new CircularViewPagerOperator(viewPager);
        circularViewPagerOperator.setNumberOfFakePages(fakePageCount);
        viewPager.addOnPageChangeListener(circularViewPagerOperator);
        circularViewPagerOperator.setOnPageChangeListener(this);

        viewPager.setCurrentItem(3);
        if (alphabets.size() != tabLayout.getTabCount()) {

            for (String str : alphabets) {
                tabLayout.addTab(tabLayout.newTab());
            }
            LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
            for (int i = 0; i < tabStrip.getChildCount(); i++) {
                tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position != 0 && position != 1 && position != 2 && position != alphabets.size() + 3
                && position != alphabets.size() + 4 && position != alphabets.size() + 5) {
            position = position - fakePageCount;
            if (tabLayout != null && tabLayout.getTabAt(position) != null) {
                tabLayout.getTabAt(position).select();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
