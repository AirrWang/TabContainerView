package com.muheda.mdtabcontainerview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.muheda.mdtabcontainerview.ExampleTwoTab;
import com.muheda.tabcontainerview.Model.BaseDto;
import com.muheda.tabcontainerview.adapter.BaseAdapter;
import com.muheda.tabcontainerview.widget.AbsTab;

public class ExampleTwoAdapter extends BaseAdapter {

    private int[][] mIconArray;

    public ExampleTwoAdapter(Context context, Fragment[] fragments, FragmentManager fragmentManager, int[][] iconArray) {
        super(new BaseDto(context, fragments, fragmentManager));

        mIconArray = iconArray;
    }

    @Override
    public AbsTab getTab(int index) {
        ExampleTwoTab tab = new ExampleTwoTab(mContext, index);
        int[] iconArray = mIconArray[index];
        tab.setIcons(iconArray[0], iconArray[1]);
        return tab;
    }
}
