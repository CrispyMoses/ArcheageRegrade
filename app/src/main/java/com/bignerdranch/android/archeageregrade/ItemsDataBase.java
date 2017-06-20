package com.bignerdranch.android.archeageregrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crispy on 16.06.2017.
 */

public class ItemsDataBase {
    private static final ItemsDataBase ourInstance = new ItemsDataBase();

    private List<Items> mItemList;
    private List<Items> mCharmList;
    private List<Items> mScrollList;

    public static ItemsDataBase getInstance() {
        return ourInstance;
    }

    private ItemsDataBase() {
        mItemList = new ArrayList<>();
        mItemList.add(new Item(false, false, R.string.common, R.drawable.common, 100));
        mItemList.add(new Item(false, false, R.string.green, R.drawable.green, 100));
        mItemList.add(new Item(false, false, R.string.blue, R.drawable.blue, 50));
        mItemList.add(new Item(false, false, R.string.unique, R.drawable.unique, 50));
        mItemList.add(new Item(false, false, R.string.epic, R.drawable.epic, 35));
        mItemList.add(new Item(false, false, R.string.legendary, R.drawable.legendary, 35));
        mItemList.add(new Item(true, true, R.string.relic, R.drawable.relic, 19.5f));
        mItemList.add(new Item(true, false, R.string.epo1, R.drawable.epo1, 8));
        mItemList.add(new Item(true, false, R.string.epo2, R.drawable.epo2, 3));
        mItemList.add(new Item(true, false, R.string.epo3, R.drawable.epo3, 2));
        mItemList.add(new Item(true, false, R.string.epo4, R.drawable.epo4, 1));
        mItemList.add(new Item(true, false, R.string.epo5, R.drawable.epo5, 100));

        mScrollList = new ArrayList<>();
        mScrollList.add(new Scroll(R.string.scroll, false, R.drawable.scroll));
        mScrollList.add(new Scroll(R.string.twin_scroll, true, R.drawable.twin_scroll));

        mCharmList = new ArrayList<>();
        mCharmList.add(new Charm(R.string.main_charm, R.drawable.main_charm, 1.75f, 3));
        mCharmList.add(new Charm(R.string.upgraded_main_charm, R.drawable.upgraded_main_charm, 1.75f, 4));
        mCharmList.add(new Charm(R.string.weak_charm, R.drawable.weak_charm, 1.5f, 5));
        mCharmList.add(new Charm(R.string.weak_universal_charm, R.drawable.weak_universal_charm, 1.5f));
        mCharmList.add(new Charm(R.string.strong_charm, R.drawable.strong_charm, 2, 5));
        mCharmList.add(new Charm(R.string.strong_universal_charm, R.drawable.strong_universal_charm, 2));
        mCharmList.add(new Charm(R.string.akhium_talisman, R.drawable.akhium_talisman, 1, 5));
        mCharmList.add(new Charm(R.string.twinkle_charm, R.drawable.twinkle_charm, 2.5f));
    }

    public List<Items> getItemList() {
        return mItemList;
    }

    public List<Items> getCharmList() {
        return mCharmList;
    }

    public List<Items> getScrollList() {
        return mScrollList;
    }
}
