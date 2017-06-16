package com.bignerdranch.android.archeageregrade;

/**
 * Created by Crispy on 14.06.2017.
 */

public class Item {

    private boolean mDestructible;
    private boolean mDegradable;
    private int mItemNameId;
    private int mDrawableId;
    private float mSuccessChance;

    public Item(boolean destructible, boolean degradable, int itemNameId, int drawableId, float successChance) {
        mDestructible = destructible;
        mDegradable = degradable;
        mItemNameId = itemNameId;
        mDrawableId = drawableId;
        mSuccessChance = successChance;
    }

    public int getItemNameId() {
        return mItemNameId;
    }

    public void setItemNameId(int itemNameId) {
        mItemNameId = itemNameId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }

    public float getSuccessChance() {
        return mSuccessChance;
    }

    public void setSuccessChance(float successChance) {
        mSuccessChance = successChance;
    }

    public boolean isDestructible() {
        return mDestructible;
    }

    public void setDestructible(boolean destructible) {
        mDestructible = destructible;
    }

    public boolean isDegradable() {
        return mDegradable;
    }

    public void setDegradable(boolean degradable) {
        mDegradable = degradable;
    }
}
