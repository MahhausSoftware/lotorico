package com.mahhaus.free.lotorico.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

import com.mahhaus.free.lotorico.R;

/**
 * Created by josias.soares on 14/12/16.
 */

public abstract class GenericsFragment extends Fragment {
    abstract void initComponents(View view);
    abstract void actionComponents();

    public void setFragment(Fragment newFragment){
        // Create new fragment and transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.include, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
