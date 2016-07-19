package com.andy.collect.ui;

import android.view.View;
import android.widget.Button;

import com.andy.collect.R;
import com.andy.collect.andylibrary.net.HttpCallback;
import com.andy.collect.andylibrary.net.nohttp.CallServer;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * desc:NoHttp之get、post请求
 * andy he
 * 2016/6/24 10:33
 */
public class TRefreshAct_1 extends BaseAct{
    private Button btn_get,btn_post;
    @Override
    protected int loadLayoutId() {
        return R.layout.act_refresh_1;
    }

    @Override

    protected void initViews() {
        btn_get = bindView(R.id.btn_get);
        btn_post = bindView(R.id.btn_post);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindListener() {
        super.bindListener();
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Request<String> request = null;
        switch (v.getId()){
            case R.id.btn_get:
                request = NoHttp.createStringRequest("http://api.nohttp.net/method", RequestMethod.GET);
                break;
            case R.id.btn_post:
                request = NoHttp.createStringRequest("http://api.nohttp.net/method", RequestMethod.POST);
                break;
            default:
                break;
        }
        if (request != null)
            http(request);
    }

    private void http(Request<String> request) {
        request.add("userName", "yolanda");// String类型
        request.add("userPass", "yolanda.pass");
        request.add("userAge", 20);// int类型
        request.add("userSex", '1');// char类型，还支持其它类型

        // 添加到请求队列
        CallServer.getRequestInstance().add(this, 0, request, new HttpCallback<String>() {

            @Override
            public void onSucceed(int what, Response<String> response) {
                int responseCode = response.getHeaders().getResponseCode();// 服务器响应码
                if (responseCode == 200) {
                    if (RequestMethod.HEAD == response.getRequestMethod())// 请求方法为HEAD时没有响应内容
                        showMsgDialog(R.string.request_succeed, R.string.request_method_head);
                    else
                        showMsgDialog(R.string.request_succeed, response.get());
                }
            }
        }, true, true);
    }
}
