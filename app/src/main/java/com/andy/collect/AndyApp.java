package com.andy.collect;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.andy.collect.andylibrary.common.CommonUtils;
import com.andy.collect.andylibrary.common.ToastUtils;
import com.andy.collect.andylibrary.net.NetworkStatusListener;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import java.util.ArrayList;

/**
 * desc:
 * andy he
 * 2016/6/27 10:01
 */
public class AndyApp extends Application {
    private BroadcastReceiver networkReceiver;
    private static AndyApp andyApp;
    private ArrayList<NetworkStatusListener> networkStatusListenerList;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isRemoteProcess()) {
            return;
        }
        andyApp = this;
        initNetworkStatusReceiver();
        // 初始化NoHttp
        NoHttp.initialize(this);
        Logger.setDebug(BuildConfig.DEBUG);
        Logger.setTag("NoHttpSample");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    private boolean isRemoteProcess() {
        return getApplicationInfo().processName.endsWith(":remote");
    }

    private void initNetworkStatusReceiver() {
        registerReceiver(networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable;
                if (!(isNetworkAvailable = CommonUtils.isNetworkAvailable(getInstance())))
                    ToastUtils.warnToast(getInstance(), "网络断开，请检查！");
                if (networkStatusListenerList != null && networkStatusListenerList.size() > 0) {
                    for (NetworkStatusListener networkStatusListener : networkStatusListenerList) {
                        networkStatusListener.onStateChange(isNetworkAvailable);
                    }
                }

            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static AndyApp getInstance() {
        return andyApp;
    }

    public synchronized void setNetworkStatusListener(NetworkStatusListener networkStatusListener) {
        if (networkStatusListenerList == null)
            networkStatusListenerList = new ArrayList<NetworkStatusListener>();
        networkStatusListenerList.add(networkStatusListener);
    }

    public synchronized void removeNetworkStatusListener(NetworkStatusListener networkStatusListener) {
        networkStatusListenerList.remove(networkStatusListener);
    }
}
