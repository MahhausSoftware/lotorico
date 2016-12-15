package com.mahhaus.free.lotorico;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mahhaus.free.lotorico.fragment.GamesFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new GamesFragment());
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

}
