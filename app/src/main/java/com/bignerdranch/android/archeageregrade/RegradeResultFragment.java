package com.bignerdranch.android.archeageregrade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Crispy on 22.06.2017.
 */

public class RegradeResultFragment extends DialogFragment {
    private static final String IS_SUCCESS = "IsSuccess";
    private static final String PREV_ITEM_ID = "PrevItem";
    private static final String CURRENT_ITEM_ID = "CurrentItem";
    private static final String RESULT_STRING_ID = "result_string";
    private static final String IS_DEGRADED = "IsDegraded";
    private static final String IS_DOUBLE_SUCCESS = "IsDoubleSuccess";
    private static final String IS_DESTRUCTED = "IsDestructed";

    private Item mPrevItem;
    private Item mCurrentItem;
    private int  mResultStringId;
    private boolean mIsDegraded;
    private boolean mIsDoubleSuccess;
    private boolean mIsDestructed;
    private boolean mIsSuccess;

    private ImageView mItemImageView;
    private ImageView mArrowImage1;
    private ImageView mArrowImage2;
    private TextView mPrevItemTextView;
    private TextView mCurrentItemTextView;
    private TextView mResultString;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.result_dialog, null);
        Bundle bundle = getArguments();

        mPrevItem = (Item) ItemsDataBase.getInstance().getItemList().get(bundle.getInt(PREV_ITEM_ID));
        mCurrentItem = (Item) ItemsDataBase.getInstance().getItemList().get(bundle.getInt(CURRENT_ITEM_ID));
        mResultStringId = bundle.getInt(RESULT_STRING_ID);
        mIsDegraded = bundle.getBoolean(IS_DEGRADED);
        mIsDestructed = bundle.getBoolean(IS_DESTRUCTED);
        mIsDoubleSuccess = bundle.getBoolean(IS_DOUBLE_SUCCESS);
        mIsSuccess = bundle.getBoolean(IS_SUCCESS);

        mItemImageView = (ImageView) v.findViewById(R.id.current_item_imgeview);
        mArrowImage1 = (ImageView) v.findViewById(R.id.result_arrow_1);
        mArrowImage2 = (ImageView) v.findViewById(R.id.result_arrow_2);
        mPrevItemTextView = (TextView) v.findViewById(R.id.prev_item_textview);
        mCurrentItemTextView = (TextView) v.findViewById(R.id.current_item_textview);
        mResultString = (TextView) v.findViewById(R.id.success_text);



        mResultString.setText(mResultStringId);
        if (mIsDestructed) {
            mItemImageView.setImageResource(R.drawable.destructed);
        } else if (mIsDegraded) {
            mItemImageView.setImageResource(mCurrentItem.getDrawableId());
            mCurrentItemTextView.setText(mCurrentItem.getNameId());
        } else if (mIsDoubleSuccess) {
            mItemImageView.setImageResource(mCurrentItem.getDrawableId());
            mCurrentItemTextView.setText(mCurrentItem.getNameId());
            mPrevItemTextView.setText(mPrevItem.getNameId());
            mArrowImage1.setImageResource(R.drawable.arrow);
            mArrowImage2.setImageResource(R.drawable.arrow);
        } else if (mIsSuccess) {
            mItemImageView.setImageResource(mCurrentItem.getDrawableId());
            mPrevItemTextView.setText(mPrevItem.getNameId());
            mArrowImage1.setImageResource(R.drawable.arrow);
            mCurrentItemTextView.setText(mCurrentItem.getNameId());
        } else {
            mItemImageView.setImageResource(mCurrentItem.getDrawableId());
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Результат")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    public static RegradeResultFragment newInstance(int prevItemId, int currentItemId,
                                                    int resultString, boolean IsSuccess,
                                                    boolean isDegraded,
                                                    boolean isDestructed,
                                                    boolean isDoubleSuccess) {
        Bundle bundle = new Bundle();
        bundle.putInt(PREV_ITEM_ID, prevItemId);
        bundle.putInt(CURRENT_ITEM_ID, currentItemId);
        bundle.putInt(RESULT_STRING_ID, resultString);
        bundle.putBoolean(IS_SUCCESS, IsSuccess);
        bundle.putBoolean(IS_DEGRADED, isDegraded);
        bundle.putBoolean(IS_DESTRUCTED, isDestructed);
        bundle.putBoolean(IS_DOUBLE_SUCCESS, isDoubleSuccess);
        RegradeResultFragment dialog = new RegradeResultFragment();
        dialog.setArguments(bundle);
        return dialog;
    }
}
