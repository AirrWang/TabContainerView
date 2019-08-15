package com.muheda.mdtabcontainerview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.muheda.mdtabcontainerview.ExampleOneTab;
import com.muheda.tabcontainerview.Model.BaseDto;
import com.muheda.tabcontainerview.adapter.BaseAdapter;
import com.muheda.tabcontainerview.widget.AbsTab;


public class ExampleOneAdapter extends BaseAdapter {

    private String[] mStrArray;

    public ExampleOneAdapter(Context context, Fragment[] fragments, FragmentManager fragmentManager, String[] strArray) {
        super(new BaseDto(context, fragments, fragmentManager));

        mStrArray = strArray;
    }

    @Override
    public AbsTab getTab(int index) {
        ExampleOneTab tab = new ExampleOneTab(mContext, index);
        tab.setText(mStrArray[index]);
        return tab;
    }
}
