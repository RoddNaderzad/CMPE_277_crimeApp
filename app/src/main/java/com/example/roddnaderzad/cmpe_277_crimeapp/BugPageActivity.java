package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
//import android.support.v4.view.ViewPager.OnPageChangeListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by roddnaderzad on 10/8/15.
 */
public class BugPageActivity extends FragmentActivity{
    private ViewPager mViewPager;
    private ArrayList<Bug> mBugs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mBugs = BugCalculator.get(this).getBugs();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Bug bug = mBugs.get(position);
                return BugFragment.newInstance(bug.getId());
            }

            @Override
            public int getCount() {
                return mBugs.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Bug bug = mBugs.get(position);
                if (bug.getTitle() != null) {
                    setTitle(bug.getTitle());
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID bugId = (UUID)getIntent().getSerializableExtra(BugFragment.EXTRA_BUG_ID);
        for (int i = 0; i <mBugs.size(); i++) {
            if (mBugs.get(i).getId().equals(bugId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
