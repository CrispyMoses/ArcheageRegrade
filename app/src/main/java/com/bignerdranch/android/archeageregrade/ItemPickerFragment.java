package com.bignerdranch.android.archeageregrade;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Crispy on 18.06.2017.
 */

public class ItemPickerFragment extends DialogFragment {

    private List<Items> mItemsList;
    private int mCode;
    private static final String DIALOG_ITEM = "DialogItem";
    public static final String EXTRA_ITEM = "ArcheageRegrade.item";
    public static final String POSITION_OF_ITEM = "ItemPosition";
    public static final String REQUEST_CODE = "RequestCode";


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.choose_dialog, null);

        mCode = getArguments().getInt(DIALOG_ITEM);
        if (mCode == RegradeFragment.ITEM_DIALOG_LIST_CODE)
            mItemsList = ItemsDataBase.getInstance().getItemList();
        else if (mCode == RegradeFragment.SCROLL_DIALOG_LIST_CODE)
            mItemsList = ItemsDataBase.getInstance().getScrollList();
        else if (mCode == RegradeFragment.CHARM_DIALOG_LIST_CODE)
            mItemsList = ItemsDataBase.getInstance().getCharmList();



        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setAdapter(new ItemAdapter(getActivity()), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK, which);
                    }
                })
                .setTitle(R.string.choose_item)
                .create();

    }

    public static ItemPickerFragment newInstance(int code) {
        Bundle bundle = new Bundle();
        bundle.putInt(DIALOG_ITEM, code);
        ItemPickerFragment dialog =  new ItemPickerFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    private class ItemAdapter extends ArrayAdapter<Items> {


        public ItemAdapter(Context context) {
            super(context, R.layout.item_sample, mItemsList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Items item = mItemsList.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_sample, null);
            }
            ((TextView) convertView.findViewById(R.id.item_text_view))
                    .setText(item.getNameId());
            ((ImageView) convertView.findViewById(R.id.item_image_view))
                    .setImageResource(item.getDrawableId());
            return convertView;
        }
    }

    private void sendResult(int resultCode, int item) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_OF_ITEM, item);
        bundle.putInt(REQUEST_CODE, mCode);
        intent.putExtra(EXTRA_ITEM, bundle);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
