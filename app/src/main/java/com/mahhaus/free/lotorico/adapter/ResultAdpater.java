package com.mahhaus.free.lotorico.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mahhaus.free.lotorico.R;

import java.util.ArrayList;

/**
 * Created by josias.soares on 14/12/16.
 */

public class ResultAdpater extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> mResults;

    public ResultAdpater(Context context, ArrayList<String> results) {
        mContext = context;
        mResults = results;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85, 85));
            textView.setPadding(8, 8, 8, 8);
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setGravity(Gravity.CENTER);
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.image_ball_sort));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mResults.get(position));
        return textView;
    }
}

