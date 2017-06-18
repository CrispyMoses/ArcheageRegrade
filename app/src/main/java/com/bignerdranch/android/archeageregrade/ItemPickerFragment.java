package com.bignerdranch.android.archeageregrade;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crispy on 18.06.2017.
 */

public class ItemPickerFragment extends DialogFragment {

    private RecyclerView mRecyclerView;
    private List<Items> mItemsList;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.choose_dialog, null);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Items[] itemses = (Items[]) ItemsDataBase.getInstance().getItemList().toArray();

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.choose_item)
                .create();

    }
}
