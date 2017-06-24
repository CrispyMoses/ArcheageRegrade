package com.bignerdranch.android.archeageregrade;


/**
 * Created by Crispy on 14.06.2017.
 */

public class Charm implements Items {
    private int mCharmNameId;
    private int mDrawableId;
    private float mMultiplyIndex;
    private int mMaxItemIndex;
    private int mMinItemIndex;
    private boolean mBlockDegradeDestruct;

    public Charm(int charmNameId, int drawableId, float multiplyIndex) {
        this(charmNameId, drawableId, multiplyIndex, 10, 0, false);
    }

    public Charm(int charmNameId, int drawableId, float multiplyIndex, int maxItemIndex) {
        this(charmNameId, drawableId, multiplyIndex, maxItemIndex, 0, false);
    }

    public Charm(int nameId, int drawableId, float multiplyIndex, int maxItemIndex, int minItemIndex, boolean blockDegradeDestruct) {
        mCharmNameId = nameId;
        mDrawableId = drawableId;
        mMultiplyIndex = multiplyIndex;
        mMaxItemIndex = maxItemIndex;
        mMinItemIndex = minItemIndex;
        mBlockDegradeDestruct = blockDegradeDestruct;
    }

    public int getNameId() {
        return mCharmNameId;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public float getMultiplyIndex() {
        return mMultiplyIndex;
    }

    public int getMaxItemIndex() {
        return mMaxItemIndex;
    }

    public int getMinItemIndex() {
        return mMinItemIndex;
    }

    public boolean isBlockDegradeDestruct() {
        return mBlockDegradeDestruct;
    }
}
