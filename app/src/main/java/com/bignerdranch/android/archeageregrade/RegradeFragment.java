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
import android.widget.Toast;

import java.util.List;
import java.util.Random;


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
    private static final String DIALOG_RESULT = "DialogResult";
    public static final int ITEM_DIALOG_LIST_CODE = 1;
    public static final int SCROLL_DIALOG_LIST_CODE = 2;
    public static final int CHARM_DIALOG_LIST_CODE = 3;
    public static final String CHARM_LIST_INDEX = "CharmListIndex";
    public static final String SCROLL_LIST_INDEX = "ScrollListIndex";
    public static final String ITEM_LIST_INDEX = "ItemListIndex";
    private static final int REQUEST_ITEM = 0;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regrade, container, false);
        if (savedInstanceState == null) {
            ItemsDataBase database = ItemsDataBase.getInstance();
            mCurrentItem = (Item) database.getItemList().get(6);
            mCharm = (Charm) database.getCharmList().get(5);
            mScroll = (Scroll) database.getScrollList().get(1);
        }
        else {
            ItemsDataBase database = ItemsDataBase.getInstance();
            mCurrentItem = (Item) database.getItemList().get(savedInstanceState.getInt(ITEM_LIST_INDEX));
            mCharm = (Charm) database.getCharmList().get(savedInstanceState.getInt(CHARM_LIST_INDEX));
            mScroll = (Scroll) database.getScrollList().get(savedInstanceState.getInt(SCROLL_LIST_INDEX));
        }

        mItemButton = (ImageButton) v.findViewById(R.id.item_button);
        mCharmButton = (ImageButton) v.findViewById(R.id.charm_button);
        mScrollButton = (ImageButton) v.findViewById(R.id.scroll_button);
        mTextChanceView = (TextView) v.findViewById(R.id.regrade_chance);
        mDestructible = (ImageView) v.findViewById(R.id.destruct_chance_image);
        mDegradable = (ImageView) v.findViewById(R.id.degrade_chance_image);
        mOkButton = (Button) v.findViewById(R.id.ok_button);
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

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Items> itemList = ItemsDataBase.getInstance().getItemList();
                RegradeResultFragment dialog = null;
                int currentIndex = itemList.indexOf(mCurrentItem);
                if (Math.random()*100 < mSuccessChance) { //success
                    if (Math.random()*100 < 10 && mScroll.isTwin() && (itemList.indexOf(mCurrentItem) < itemList.size() - 2)) { //double
                        mPrevItem = mCurrentItem;
                        mCurrentItem = (Item) itemList.get(currentIndex + 2);
                        dialog = RegradeResultFragment.newInstance(currentIndex,
                                itemList.indexOf(mCurrentItem),
                                R.string.result_unbelievable_success,
                                false, false, false, true);
                    } else {
                        mPrevItem = mCurrentItem;
                        mCurrentItem = (Item) itemList.get(currentIndex + 1);
                        dialog = RegradeResultFragment.newInstance(currentIndex,
                                itemList.indexOf(mCurrentItem),
                                R.string.result_success,
                                true, false, false, false);
                    }
                }
                else { //fail
                    if (mCurrentItem.isDegradable() && Math.random()*100 <= 50 && !mCharm.isBlockDegradeDestruct()) { //degrade
                        mPrevItem = mCurrentItem;
                        mCurrentItem = (Item) itemList.get(currentIndex - 3);
                        dialog = RegradeResultFragment.newInstance(currentIndex,
                                itemList.indexOf(mCurrentItem),
                                R.string.result_degrade,
                                false, true, false, false);
                    }
                    else if (mCurrentItem.isDestructible() && !mCharm.isBlockDegradeDestruct()) { //destruct
                        mPrevItem = mCurrentItem;
                        mCurrentItem = null;
                        dialog = RegradeResultFragment.newInstance(currentIndex,
                                0,
                                R.string.result_destruct,
                                false, false, true, false);
                    } else {
                        dialog = RegradeResultFragment.newInstance(currentIndex,
                                itemList.indexOf(mCurrentItem),
                                R.string.result_fail,
                                false, false, false, false);
                    }
                }

                dialog.show(getFragmentManager(),DIALOG_RESULT);
                updateUI();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        if (mCharm.getMaxItemIndex() >= ItemsDataBase.getInstance().getItemList().indexOf(mCurrentItem) &&
                mCharm.getMinItemIndex() <= ItemsDataBase.getInstance().getItemList().indexOf(mCurrentItem) ||
                mCurrentItem == null)
            mCharmButton.setImageResource(mCharm.getDrawableId());
        else {
            mCharm = (Charm) ItemsDataBase.getInstance().getCharmList().get(0);
            mCharmButton.setImageDrawable(null);
        }
        if (mCurrentItem != null)  {
            mItemButton.setImageResource(mCurrentItem.getDrawableId());
            if (mCurrentItem.isDegradable() && !mCharm.isBlockDegradeDestruct()) mDegradable.setImageResource(R.drawable.true_image);
            else mDegradable.setImageResource(R.drawable.false_image);
            if (mCurrentItem.isDestructible() && !mCharm.isBlockDegradeDestruct()) mDestructible.setImageResource(R.drawable.true_image);
            else mDestructible.setImageResource(R.drawable.false_image);
        }
        else {
            mItemButton.setImageDrawable(null);
            mDestructible.setImageDrawable(null);
            mDegradable.setImageDrawable(null);
        }

        mScrollButton.setImageResource(mScroll.getDrawableId());
        List<Items> itemList= ItemsDataBase.getInstance().getItemList();
        if (mCurrentItem != null && itemList.indexOf(mCurrentItem) != itemList.size() - 1) {
            mSuccessChance = (int) (mCurrentItem.getSuccessChance() * (mCharm != null ? mCharm.getMultiplyIndex() : 1));
            mSuccessChance = mSuccessChance > 100 ? 100 : mSuccessChance;
            mTextChanceView.setText(mSuccessChance + "%");
            mOkButton.setEnabled(true);
        }
        else {
            mSuccessChance = 0;
            mTextChanceView.setText(null);
            mOkButton.setEnabled(false);
        }
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ItemsDataBase dataBase = ItemsDataBase.getInstance();
        outState.putInt(ITEM_LIST_INDEX,dataBase.getItemList().indexOf(mCurrentItem));
        outState.putInt(SCROLL_LIST_INDEX,dataBase.getScrollList().indexOf(mScroll));
        outState.putInt(CHARM_LIST_INDEX,dataBase.getCharmList().indexOf(mCharm));
    }
}
