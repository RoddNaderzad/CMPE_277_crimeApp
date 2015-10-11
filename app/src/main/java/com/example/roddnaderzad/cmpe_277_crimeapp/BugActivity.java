package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import java.util.UUID;

public class BugActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        UUID bugId = (UUID)getIntent().getSerializableExtra(BugFragment.EXTRA_BUG_ID);

        return BugFragment.newInstance(bugId);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
