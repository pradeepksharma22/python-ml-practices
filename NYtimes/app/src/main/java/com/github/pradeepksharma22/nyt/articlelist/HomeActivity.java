package com.github.pradeepksharma22.nyt.articlelist;

import android.os.Bundle;

import com.github.pradeepksharma22.nyt.R;
import com.github.pradeepksharma22.nyt.common.BaseActivity;
import com.github.pradeepksharma22.nyt.common.NavigationManager;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NavigationManager.init(this);

        NavigationManager.getInstance().addFragment(ArticlesFragment.newInstance(),false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NavigationManager.getInstance().destroy();
    }
}
