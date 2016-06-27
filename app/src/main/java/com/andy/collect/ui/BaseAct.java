package com.andy.collect.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.andy.collect.R;
import com.andy.collect.andylibrary.common.ToastUtils;
import com.andy.collect.andylibrary.widget.dialog.WebDialog;
import com.andy.collect.andylibrary.net.HttpInterface;
import com.andy.collect.andylibrary.net.nohttp.CallServer;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;
import com.yolanda.nohttp.tools.HeaderParser;

public abstract class BaseAct extends AppCompatActivity implements HttpInterface {
    protected View title, more;
    protected Toolbar mToolbar;
    public static final String MSG_USER_EXIT_ACTION = "MSG_USER_EXIT_ACTION";//退出程序

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(loadLayoutId());
        initToolbar();
        initViews();
        initData();
        registerMessageReceiver();
        bindListener();
        networkProcess();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMsgReceiver);
        CallServer.getRequestInstance().cancelBySign(this);
        super.onDestroy();
    }

    protected void initToolbar() {
        mToolbar = bindView(R.id.tb);
        if (mToolbar == null)
            return;
        setSupportActionBar(mToolbar);
        title = bindView(R.id.tb_title);
        more = bindView(R.id.tb_more);
    }

    protected <T extends View> T bindView(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }

    protected <T extends View> T bindView(View view, @IdRes int viewId) {
        return (T) view.findViewById(viewId);
    }

    protected BroadcastReceiver mMsgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(MSG_USER_EXIT_ACTION)) {
                finish();
            }
        }
    };

    protected void registerMessageReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MSG_USER_EXIT_ACTION);
        registerReceiver(mMsgReceiver, filter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//监听tb的navigationIcon
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setTitleStr(@NonNull String titleStr) {
        ((TextView) title).setText(titleStr);
    }

    /**
     * 加载布局文件
     */
    protected abstract int loadLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化数剧
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected void bindListener() {

    }

    /**
     * 网络请求
     */
    protected void networkProcess() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showSuccessToast(@NonNull String msg) {
        ToastUtils.successToast(this, msg);
    }

    @Override
    public void showFailToast(@NonNull String msg) {
        ToastUtils.errorToast(this, msg);
    }

    protected void showWarnToast(@NonNull String msg) {
        ToastUtils.warnToast(this, msg);
    }

    @Override
    public void showNotifyDialog(@NonNull String msg) {

    }

    @Override
    public void showMsgDialog(int title, int message) {
        showMessageDialog(getText(title), getText(message));
    }

    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.know, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void showWebDialog(Response<?> response) {
        String result = StringRequest.parseResponseString(response.url(), response.getHeaders(), response.getByteArray());
        WebDialog webDialog = new WebDialog(this);
        String contentType = response.getHeaders().getContentType();
        webDialog.loadUrl(result, contentType, HeaderParser.parseHeadValue(contentType, "charset", "utf-8"));
        webDialog.show();
    }

    @Override
    public Activity getActivity() {
        return null;
    }


}
