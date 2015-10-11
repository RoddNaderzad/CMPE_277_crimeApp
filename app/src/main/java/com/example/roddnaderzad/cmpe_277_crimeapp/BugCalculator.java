package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by roddnaderzad on 10/6/15.
 */
public class BugCalculator {
    private ArrayList<Bug> mBugs;

    private static BugCalculator sBugCalculator;
    private Context mAppContext;

    private BugCalculator(Context appContext) {
        mAppContext = appContext;
        mBugs = new ArrayList<Bug>();
        for (int i = 0; i < 100; i++){
            Bug b = new Bug();
            b.setTitle("Bug #" + i);
            b.setSolved(i % 2 == 0);
            mBugs.add(b);
        }
    }

    public static BugCalculator get(Context c) {
        if (sBugCalculator == null) {
            sBugCalculator = new BugCalculator(c.getApplicationContext());
        }
        return sBugCalculator;
    }

    public ArrayList<Bug> getBugs() {
        return mBugs;
    }

        public Bug getBug(UUID id){
            for (Bug b : mBugs) {
                if (b.getId().equals(id))
                    return b;
            }
            return null;
        }

}

