package com.mahhaus.free.lotorico.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.mahhaus.free.lotorico.R;
import com.mahhaus.free.lotorico.adapter.ImageAdapter;
import com.mahhaus.free.lotorico.props.GameUrlEnum;

/**
 * Created by josias.soares on 14/12/16.
 */

public class GamesFragment extends GenericsFragment {
    private GridView mGridview;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (LinearLayout) inflater.inflate(R.layout.fragment_games, container, false);
        mContext = getActivity();

        initComponents(view);
        actionComponents();

        return view;
    }

    @Override
    void initComponents(View view) {
        mGridview = (GridView) view.findViewById(R.id.gridViewButton);
        mGridview.setAdapter(new ImageAdapter(getActivity()));
    }

    @Override
    void actionComponents() {
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("URL", GameUrlEnum.getGameUrl(position));

                Fragment fragment = GameUrlEnum.getFragmentPos(position);
                fragment.setArguments(bundle);

                setFragment(fragment);
            }
        });
    }
}
