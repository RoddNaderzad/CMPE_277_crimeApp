package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by roddnaderzad on 10/7/15.
 */
public class BugListFragment extends ListFragment{

    private static final String TAG = "BugListFragment";
    private ArrayList<Bug> mBugs;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.bug_title);
        mBugs = BugCalculator.get(getActivity()).getBugs();

        BugAdapter adapter = new BugAdapter(mBugs);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bug b = ((BugAdapter)getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), BugPageActivity.class);
        i.putExtra(BugFragment.EXTRA_BUG_ID, b.getId());
        startActivity(i);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((BugAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class BugAdapter extends ArrayAdapter<Bug> {
        public BugAdapter(ArrayList<Bug> bugs) {
            super(getActivity(), 0, bugs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_bug, null);
            }

            Bug b = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.bug_list_item_titleTextView);
            titleTextView.setText(b.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.bug_list_item_dateTextView);
            dateTextView.setText(b.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.bug_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(b.isSolved());

            return convertView;
        }
    }
}
