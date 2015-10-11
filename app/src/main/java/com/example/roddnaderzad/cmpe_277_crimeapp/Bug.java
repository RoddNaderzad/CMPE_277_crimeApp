package com.example.roddnaderzad.cmpe_277_crimeapp;

import java.util.Date;
import java.util.UUID;

/**
 * Created by roddnaderzad on 10/5/15.
 */
public class Bug {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Bug() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString(){
        return mTitle;
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
    }

    public boolean isSolved(){
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
