package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Scroll {
    private boolean mTwin;
    private String mScrollName;
    private int DrawableId;

    public Scroll(String name,boolean twin) {
        mTwin = twin;
        mScrollName = name;
    }

    public boolean isTwin() {
        return mTwin;
    }

    public void setTwin(boolean twin) {
        mTwin = twin;
    }

    public String getScrollName() {
        return mScrollName;
    }

    public void setScrollName(String scrollName) {
        mScrollName = scrollName;
    }

    public int getDrawableId() {
        return DrawableId;
    }

    public void setDrawableId(int drawableId) {
        DrawableId = drawableId;
    }
}
