package com.dz.restaurant.helpers;

import android.app.Activity;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dz.restaurant.R;

public class FragmentHelper {

    private Activity activity;

    //Public Constructor
    public FragmentHelper(Activity activity){
        this.activity = activity;
    }

    //Getters
    public Activity getActivity(){
        return this.activity;
    }

    //Setters
    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void initFragment(Fragment fragment, FragmentManager fragmentManager, String tag, boolean addToBackStack){
        if(addToBackStack) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment, tag).addToBackStack(null);
            fragmentTransaction.commit();
        }else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment, tag);
            fragmentTransaction.commit();
        }
    }

    public void initFragment(Fragment fragment, FragmentManager fragmentManager,String tag){
        initFragment(fragment,fragmentManager, tag,true);
    }

    public void initFragment(Fragment fragment, FragmentManager fragmentManager){
        initFragment(fragment, fragmentManager, "");
    }

    public void initFragment(DialogFragment fragment, FragmentManager fragmentManager){
        initFragment(fragment, fragmentManager,"");
    }

    public void initFragment(DialogFragment fragment, FragmentManager fragmentManager, String tag){
        fragment.show(fragmentManager, tag);
    }

}
