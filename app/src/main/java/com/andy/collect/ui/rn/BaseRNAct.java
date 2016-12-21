package com.andy.collect.ui.rn;

import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

public abstract class BaseRNAct extends ReactActivity /*implements HttpInterface, View.OnClickListener*/ {
   /* protected View title, more;
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
        //Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Bugtags.onPause(this);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMsgReceiver);
        CallServer.getRequestInstance().cancelBySign(this);
        super.onDestroy();
    }

    @Override
    public void setActionBar(android.widget.Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    protected void initToolbar() {
        mToolbar = bindView(R.id.tb);
        if (mToolbar == null)
            return;
        //setSupportActionBar(mToolbar);
        setActionBar(mToolbar);
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

    *//**
     * 加载布局文件
     *//*
    protected abstract int loadLayoutId();

    *//**
     * 初始化控件
     *//*
    protected abstract void initViews();

    *//**
     * 初始化数剧
     *//*
    protected abstract void initData();

    *//**
     * 设置监听
     *//*
    protected void bindListener() {

    }

    *//**
     * 网络请求
     *//*
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


    public void showMsgDialog(int title, int message) {
        showMsgDialog(getText(title), getText(message));
    }

    @Override
    public void showMsgDialog(int title, CharSequence message) {
        showMsgDialog(getText(title), message);
    }

    public void showMsgDialog(CharSequence title, CharSequence message) {
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
        return this;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //Bugtags.onDispatchTouchEvent(this, ev);
        return super.dispatchTouchEvent(ev);
    }
*/
    @Nullable
    @Override
    protected abstract String getMainComponentName() ;
}
