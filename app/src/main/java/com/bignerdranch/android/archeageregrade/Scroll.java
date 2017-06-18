package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Scroll implements Items {
    private boolean mTwin;
    private int mScrollNameId;
    private int mDrawableId;

    public Scroll(int nameId, boolean twin, int DrawableId) {
        mTwin = twin;
        mScrollNameId = nameId;
        mDrawableId = DrawableId;
    }

    public boolean isTwin() {
        return mTwin;
    }

    public void setTwin(boolean twin) {
        mTwin = twin;
    }

    public int getNameId() {
        return mScrollNameId;
    }

    public void setNameId(int scrollNameId) {
        mScrollNameId = scrollNameId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }
}
