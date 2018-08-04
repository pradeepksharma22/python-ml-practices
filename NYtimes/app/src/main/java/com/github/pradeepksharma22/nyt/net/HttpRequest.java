package com.github.pradeepksharma22.nyt.net;

import com.github.pradeepksharma22.nyt.Constants;

public interface HttpRequest {
    String API_KEY = Constants.API_KEY;
    String BASE_URL = Constants.BASE_URL;
    void execute(HttpResponseListener listener);
}
