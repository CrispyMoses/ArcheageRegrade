package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Charm {
    private String mCharmName;
    private int mDrawableId;
    private float mMultiplyIndex;

    public Charm(String name, int drawableId, float multiplyIndex) {
        mCharmName = name;
        mDrawableId = drawableId;
    }

    public String getCharmName() {
        return mCharmName;
    }

    public void setCharmName(String charmName) {
        mCharmName = charmName;
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
