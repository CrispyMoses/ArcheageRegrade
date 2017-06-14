package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Item {
    private boolean mTwin;

    public Item(boolean twin) {
        mTwin = twin;
    }

    public boolean isTwin() {
        return mTwin;
    }

    public void setTwin(boolean twin) {
        mTwin = twin;
    }
}
