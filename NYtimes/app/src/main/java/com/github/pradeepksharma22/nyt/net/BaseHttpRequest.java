package com.github.pradeepksharma22.nyt.net;

import okhttp3.OkHttpClient;

public abstract class BaseHttpRequest implements HttpRequest{
    private static OkHttpClient client;
    static {
        client = new OkHttpClient();
    }


    protected OkHttpClient getAPIClient(){
        return client;
    }
    public abstract String getURL();

}
