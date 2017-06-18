package com.bignerdranch.android.archeageregrade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crispy on 18.06.2017.
 */

public class ItemPickerFragment extends DialogFragment {

    private List<Items> mItemsList;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.choose_dialog, null);

        mItemsList = ItemsDataBase.getInstance().getItemList();


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setAdapter(new ItemAdapter(getActivity()), null)
                .setTitle(R.string.choose_item)
                .create();

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

}
