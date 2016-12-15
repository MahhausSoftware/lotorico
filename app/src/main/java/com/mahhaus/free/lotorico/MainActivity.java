package com.mahhaus.free.lotorico;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mahhaus.free.lotorico.fragment.GamesFragment;


public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private FrameLayout mAdFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdFrameLayout = (FrameLayout) findViewById(R.id.admob_60seg);
        mAdView = new AdView(this);
        mAdView.setAdUnitId(getString(R.string.admob_banner_60seg));
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdFrameLayout.addView(mAdView);

        mAdView.loadAd(new AdRequest.Builder().build());

        setFragment(new GamesFragment());
    }



    @Override
    protected void onPause() {
        if (mAdView != null){
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null){
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null){
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public void setFragment(Fragment newFragment){


        // Create new fragment and transaction
//        Fragment newFragment = new ExampleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.include, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
