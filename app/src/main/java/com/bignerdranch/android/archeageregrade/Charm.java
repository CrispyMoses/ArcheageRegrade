package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Charm {
    private int mStringId;
    private int mDrawableId;
    private float mSuccesChance;

    public Charm(int stringId, int drawableId) {
        mStringId = stringId;
        mDrawableId = drawableId;
    }

    public int getStringId() {
        return mStringId;
    }

    public void setStringId(int stringId) {
        mStringId = stringId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }

    public float getSuccesChance() {
        return mSuccesChance;
    }

    public void setSuccesChance(float succesChance) {
        mSuccesChance = succesChance;
    }
}
