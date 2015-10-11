package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.support.v4.app.Fragment;

/**
 * Created by roddnaderzad on 10/7/15.
 */
public class BugListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new BugListFragment();
    }
}
