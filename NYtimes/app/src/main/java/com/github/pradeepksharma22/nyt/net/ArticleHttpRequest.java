package com.github.pradeepksharma22.nyt.net;

import okhttp3.Request;

/**
 * Returns most viewed articles based on section and period
 */
public class ArticleHttpRequest extends BaseHttpRequest {

    public static class Param{
        private String section;
        private int period;
        private int offset;

        public Param(String section, int period, int offset) {
            this.section = section;
            this.period = period;
            this.offset = offset;
        }
    }

    private Param param;

    public ArticleHttpRequest(Param param) {
        this.param = param;
    }

    @Override
    public void execute(HttpResponseListener listener) {
        Request.Builder builder = new Request.Builder();
        builder.url(getURL());
        Request request = builder.build();
        getAPIClient().newCall(request).enqueue(listener);
    }

    @Override
    public String getURL() {
        StringBuilder sb = new StringBuilder(BASE_URL)
                .append("/svc/mostpopular/v2/mostviewed/")
                .append(param.section)
                .append('/')
                .append(param.period)
                .append(".json?")
                .append("api-key=")
                .append(API_KEY)
                .append('&')
                .append("offset=")
                .append(param.offset);
        return sb.toString();
    }
}
