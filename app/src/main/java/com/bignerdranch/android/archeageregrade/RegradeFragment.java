package com.bignerdranch.android.archeageregrade;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Crispy on 14.06.2017.
 */

public class RegradeFragment extends Fragment {

    private Item mCurrentItem;
    private Item mPrevItem;
    private Scroll mScroll;
    private Charm mCharm;

    private ImageButton mItemButton;
    private ImageButton mCharmButton;
    private ImageButton mScrollButton;

    private TextView mTextChanceView;
    private ImageView mDestructible;
    private ImageView mDegradable;

    private Button mOkButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regrade, container, false);

        ItemsDataBase database = ItemsDataBase.getInstance();
        mCurrentItem = database.getItemList().get(8);
        mCharm = database.getCharmList().get(5);
        mScroll = database.getScrollList().get(1);

        mItemButton = (ImageButton) v.findViewById(R.id.item_button);
        mCharmButton = (ImageButton) v.findViewById(R.id.charm_button);
        mScrollButton = (ImageButton) v.findViewById(R.id.scroll_button);
        updateUI();

        mTextChanceView = (TextView) v.findViewById(R.id.regrade_chance);
        final int chance = (int) (mCurrentItem.getSuccessChance() * mCharm.getMultiplyIndex());
        mTextChanceView.setText(( chance > 100 ? 100 : chance )+ "%");

        mOkButton = (Button) v.findViewById(R.id.ok_button);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mItemButton.setImageResource(mCurrentItem.getDrawableId());
        mCharmButton.setImageResource(mCharm.getDrawableId());
        mScrollButton.setImageResource(mScroll.getDrawableId());
    }
}
