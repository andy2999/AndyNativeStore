package com.andy.collect.andylibrary.net;

import com.andy.collect.andylibrary.net.nohttp.HttpListener;
import com.yolanda.nohttp.rest.Response;

/**
 * desc:
 * andy he
 * 2016/6/28 16:20
 */

public abstract class HttpCallback<T> implements HttpListener<T> {
    @Override
    public void onSucceed(int what, Response<T> response) {

    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }
}
