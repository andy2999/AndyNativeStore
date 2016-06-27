package com.andy.collect.andylibrary.net;

/**
 * desc:
 *
 * @author andy he
 * @time 2016/6/27 11:25
 */
public interface NetworkStatusListener {
    /**
     * 网络回调
     * @param status true有网
     */
    void onStateChange(boolean status);
}
