package com.mahhaus.free.lotorico.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mahhaus.free.lotorico.R;

import static com.mahhaus.free.lotorico.props.GameUrlEnum.DUPLASENA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.FEDERAL;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.INSTANTANEA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.LOTECA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.LOTOFACIL;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.LOTOGOL;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.LOTOMANIA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.MEGASENA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.QUINA;
import static com.mahhaus.free.lotorico.props.GameUrlEnum.TIMEMANIA;

/**
 * Created by josias.soares on 14/12/16.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds = {
            MEGASENA.getImage(),
            LOTOMANIA.getImage(),
            TIMEMANIA.getImage(),
            QUINA.getImage(),
            DUPLASENA.getImage(),
            FEDERAL.getImage(),
            INSTANTANEA.getImage(),
            LOTECA.getImage(),
            LOTOGOL.getImage(),
            LOTOFACIL.getImage()
    };

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.image_button));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
