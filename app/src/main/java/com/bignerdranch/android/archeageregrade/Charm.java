package com.bignerdranch.android.archeageregrade;

import android.os.Parcelable;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Charm implements Items {
    private int mCharmNameId;
    private int mDrawableId;
    private float mMultiplyIndex;
    private int mMaxItemIndex;

    public Charm(int charmNameId, int drawableId, float multiplyIndex) {
        this(charmNameId, drawableId, multiplyIndex, 10);
    }

    public Charm(int nameId, int drawableId, float multiplyIndex, int maxItemIndex) {
        mCharmNameId = nameId;
        mDrawableId = drawableId;
        mMultiplyIndex = multiplyIndex;
        mMaxItemIndex = maxItemIndex;
    }

    public int getNameId() {
        return mCharmNameId;
    }

    public void setNameId(int charmNameId) {
        mCharmNameId = charmNameId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }

    public float getMultiplyIndex() {
        return mMultiplyIndex;
    }

    public void setMultiplyIndex(float multiplyIndex) {
        mMultiplyIndex = multiplyIndex;
    }

    public int getMaxItemIndex() {
        return mMaxItemIndex;
    }

    public void setMaxItemIndex(int maxItemIndex) {
        mMaxItemIndex = maxItemIndex;
    }
}
