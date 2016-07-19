package com.andy.collect.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.collect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:RecyclerViewAdapter 适配器
 * andy he
 * 2016/7/18 17:31
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_GROUP = 1;
    private List<String> mData = new ArrayList<>();
    private Context mContext;

    private boolean isTitle(int pos) {
        if (mData.get(pos).startsWith("this is title:")) {
            return true;
        }
        return false;
    }

    public RecyclerViewAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case TYPE_IMAGE:
                ViewGroup vImage = (ViewGroup) mInflater.inflate(R.layout.item_image, parent, false);
                ImageViewHolder vhImage = new ImageViewHolder(vImage);
                return vhImage;
            case TYPE_GROUP:
                ViewGroup vGroup = (ViewGroup) mInflater.inflate(R.layout.item_text, parent, false);
                GroupViewHolder vhGroup = new GroupViewHolder(vGroup);
                return vhGroup;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_IMAGE:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.mImage.setImageResource(R.drawable.man);
                imageViewHolder.mTitle.setText(mData.get(position));
                break;
            case TYPE_GROUP:
                GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
                groupViewHolder.mTitle.setText(mData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (!isTitle(position)) {
            viewType = TYPE_IMAGE;
        } else {
            viewType = TYPE_GROUP;
        }
        return viewType;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;

        public GroupViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text);
        }

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.text);
            mImage = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
