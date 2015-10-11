package com.example.roddnaderzad.cmpe_277_crimeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import java.util.Date;
import java.util.UUID;

/**
 * Created by roddnaderzad on 10/5/15.
 */
public class BugFragment extends Fragment{
    public static final String EXTRA_BUG_ID = "com.example.roddnaderzad.cmpe_277_crimeapp.bug_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private Bug mBug;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static BugFragment newInstance(UUID bugId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_BUG_ID, bugId);

        BugFragment fragment = new BugFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID bugId = (UUID)getArguments().getSerializable(EXTRA_BUG_ID);
        mBug = BugCalculator.get(getActivity()).getBug(bugId);
        //mBug = new Bug();
    }

    public void updateDate(){
        mDateButton.setText(mBug.getDate().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_bug, parent, false);
        mTitleField = (EditText)v.findViewById(R.id.bug_title);
        mTitleField.setText(mBug.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mBug.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //blank
            }
        });

        mDateButton = (Button)v.findViewById(R.id.bug_date);
        //mDateButton.setText(mBug.getDate().toString());
        updateDate();
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mBug.getDate());
                dialog.setTargetFragment(BugFragment.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.bug_solved);
        mSolvedCheckBox.setChecked(mBug.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mBug.setSolved(b);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mBug.setDate(date);
            //mDateButton.setText(mBug.getDate().toString());
            updateDate();
        }
    }
}
