package com.bignerdranch.android.archeageregrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


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

    private static final String DIALOG_ITEM = "DialogItem";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regrade, container, false);

        ItemsDataBase database = ItemsDataBase.getInstance();
        mCurrentItem = (Item) database.getItemList().get(8);
        mCharm = (Charm) database.getCharmList().get(5);
        mScroll = (Scroll) database.getScrollList().get(1);

        mItemButton = (ImageButton) v.findViewById(R.id.item_button);
        mCharmButton = (ImageButton) v.findViewById(R.id.charm_button);
        mScrollButton = (ImageButton) v.findViewById(R.id.scroll_button);
        updateUI();

        mItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(1);
            }
        });
        mScrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(2);
            }
        });
        mCharmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(3);
            }
        });

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

    private void onclickSetter(int code) {
        FragmentManager fm = getFragmentManager();
        ItemPickerFragment dialog = ItemPickerFragment.newInstance(code);
        dialog.show(fm, DIALOG_ITEM);
    }
}
