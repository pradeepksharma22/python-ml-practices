package com.github.pradeepksharma22.nyt.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.github.pradeepksharma22.nyt.R;

public class NavigationManager {
    private static NavigationManager mInstance;
    private BaseActivity activity;

    public static synchronized NavigationManager init(BaseActivity activity) {
        mInstance = new NavigationManager(activity);
        return mInstance;
    }

    public static NavigationManager getInstance() {
        return mInstance;
    }

    public NavigationManager(BaseActivity activity) {
        this.activity = activity;
    }

    public void destroy(){
        this.activity = null;
    }
    public void addFragment(Fragment fragment, boolean addToStack) {

        FragmentTransaction ft = activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if(addToStack){
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }

    public void removeFragment(Fragment fragment,boolean addToStack) {
        FragmentTransaction ft = activity.getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment);
        if(addToStack){
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }
    public void replaceFragment(Fragment fragment,boolean addToStack) {

        FragmentTransaction ft = activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if(addToStack){
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commitAllowingStateLoss();
    }
}
