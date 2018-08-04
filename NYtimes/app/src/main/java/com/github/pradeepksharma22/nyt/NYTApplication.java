package com.github.pradeepksharma22.nyt;

import android.app.Application;

public class NYTApplication extends Application {
    private NYTApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public NYTApplication getInstance() {
        return instance;
    }
}
