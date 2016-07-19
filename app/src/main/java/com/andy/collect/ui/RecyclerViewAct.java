package com.andy.collect.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andy.collect.R;
import com.andy.collect.adaper.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:RecyclerView分组功能DEMO
 * andy he
 * 2016/7/18 17:21
 */
public class RecyclerViewAct extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // setlayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //setAdapter
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("this is content:" + i);
        }
        mData.add(3,"this is title: 1");
        mData.add(15,"this is title: 2");
        mData.add(20,"this is title: 3");
        mData.add(60,"this is title: 4");
        mData.add(77,"this is title: 5");
        mAdapter = new RecyclerViewAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
    }

}


