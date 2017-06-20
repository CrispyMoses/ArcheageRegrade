package com.bignerdranch.android.archeageregrade;

import android.app.Activity;
import android.content.Intent;
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
    private int mSuccessChance;

    private ImageButton mItemButton;
    private ImageButton mCharmButton;
    private ImageButton mScrollButton;

    private TextView mTextChanceView;
    private ImageView mDestructible;
    private ImageView mDegradable;

    private Button mOkButton;

    private static final String DIALOG_ITEM = "DialogItem";
    public static final int ITEM_DIALOG_LIST_CODE = 1;
    public static final int SCROLL_DIALOG_LIST_CODE = 2;
    public static final int CHARM_DIALOG_LIST_CODE = 3;
    private static final int REQUEST_ITEM = 0;

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
        mTextChanceView = (TextView) v.findViewById(R.id.regrade_chance);
        updateUI();

        mItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(ITEM_DIALOG_LIST_CODE);
            }
        });
        mScrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(SCROLL_DIALOG_LIST_CODE);
            }
        });
        mCharmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickSetter(CHARM_DIALOG_LIST_CODE);
            }
        });

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
        mSuccessChance = (int) (mCurrentItem.getSuccessChance() * mCharm.getMultiplyIndex());
        mTextChanceView.setText(( mSuccessChance > 100 ? 100 : mSuccessChance )+ "%");
    }

    private void onclickSetter(int code) {
        FragmentManager fm = getFragmentManager();
        ItemPickerFragment dialog = ItemPickerFragment.newInstance(code, ItemsDataBase.getInstance().getItemList().indexOf(mCurrentItem));
        dialog.setTargetFragment(RegradeFragment.this, REQUEST_ITEM);
        dialog.show(fm, DIALOG_ITEM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_ITEM) {
            Bundle bundle = data.getBundleExtra(ItemPickerFragment.EXTRA_ITEM);
            int position = bundle.getInt(ItemPickerFragment.POSITION_OF_ITEM);
            int code = bundle.getInt(ItemPickerFragment.REQUEST_CODE);
            if (code == ITEM_DIALOG_LIST_CODE) mCurrentItem =  (Item) ItemsDataBase.getInstance().getItemList().get(position);
            else if (code == SCROLL_DIALOG_LIST_CODE) mScroll = (Scroll) ItemsDataBase.getInstance().getScrollList().get(position);
            else if (code == CHARM_DIALOG_LIST_CODE) mCharm = (Charm) ItemsDataBase.getInstance().getCharmList().get(position);
            updateUI();
        }
    }
}
