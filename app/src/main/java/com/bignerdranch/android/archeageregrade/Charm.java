package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Charm {
    private int mCharmNameId;
    private int mDrawableId;
    private float mMultiplyIndex;

    public Charm(int nameId, int drawableId, float multiplyIndex) {
        mCharmNameId = nameId;
        mDrawableId = drawableId;
    }

    public int getCharmNameId() {
        return mCharmNameId;
    }

    public void setCharmNameId(int charmNameId) {
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
}
