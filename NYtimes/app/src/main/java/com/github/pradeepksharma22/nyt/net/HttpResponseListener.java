package com.github.pradeepksharma22.nyt.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class HttpResponseListener implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {
        onFail(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
            onSuccess(response);
    }

    public abstract void onFail(Exception e);
    public abstract void onSuccess(Response response);
}
