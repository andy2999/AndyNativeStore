package com.andy.collect.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
public class RecyclerViewAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // setlayoutManager
        gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mData.get(position).startsWith("title"))
                    return gridLayoutManager.getSpanCount();
                else
                    return 1;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //setAdapter
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("content:" + i);
        }
        mData.add(3, "title: 1");
        mData.add(15, "title: 2");
        mData.add(20, "title: 3");
        mData.add(60, "title: 4");
        mData.add(77, "title: 5");
        mAdapter = new RecyclerViewAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
    }

}


