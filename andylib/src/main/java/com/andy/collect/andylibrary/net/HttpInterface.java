package com.andy.collect.andylibrary.net;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.yolanda.nohttp.rest.Response;

public interface HttpInterface {

    void showLoadingView();

    void hideLoadingView();

    void showSuccessToast(@NonNull String msg);

    void showFailToast(@NonNull String msg);

    void showNotifyDialog(@NonNull String msg);

    void showMsgDialog(int title, CharSequence msg);

    void showWebDialog(Response<?> response);

    Activity getActivity();

}