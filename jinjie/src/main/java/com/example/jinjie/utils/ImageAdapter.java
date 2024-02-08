package com.example.jinjie.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private static final String TAG = "ImageAdapter";
    private int[] mRes;
    private Context mContext;

    public ImageAdapter(int[] res, Context context) {
        this.mRes = res;
        this.mContext = context;
    }

    @Override
    public int getCount() {
//        return mRes.length;
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return mRes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: position " + position);
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(mRes[position % mRes.length]);
        imageView.setLayoutParams(new Gallery.LayoutParams(400, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return imageView;
    }
}
