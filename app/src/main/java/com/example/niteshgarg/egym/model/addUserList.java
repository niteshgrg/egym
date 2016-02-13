package com.example.niteshgarg.egym.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by niteshgarg on 13/02/16.
 */
public class addUserList {

    private ArrayList<results> mResultsArrayList = new ArrayList<results>();
    static addUserList sUserDataList;
    Context mContext;

    addUserList(Context context) {
        mContext = context;
    }

    public static addUserList get(Context context) {
        if (sUserDataList == null) {
            sUserDataList = new addUserList(context);
        }
        return sUserDataList;
    }

    public ArrayList<results> getResultsArrayList() {
        return mResultsArrayList;
    }

    public void setResultsArrayList(ArrayList<results> resultsList) {
        for (results Results : resultsList)
            mResultsArrayList.add(Results);
    }

    public results getSingleResultByPosition(int pos) {
        return mResultsArrayList.get(pos);
    }

    public void clearList() {
        mResultsArrayList.clear();
    }
}
