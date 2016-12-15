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
 * Created by josias on 14/12/16.
 */

public class WinnersAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mResults;

    public WinnersAdapter(Context context, ArrayList<String> results) {
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
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (position % 2 == 0) {
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setPadding(8, 8, 8, 5);
                textView.setBackgroundColor(Color.GRAY);
            } else {
                textView.setTextColor(Color.GRAY);
                textView.setPadding(8, 8, 8, 10);
                textView.setBackgroundColor(Color.WHITE);
            }
            textView.setGravity(Gravity.LEFT);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(mResults.get(position));
        return textView;
    }
}
