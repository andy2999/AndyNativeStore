package com.andy.collect.andylibrary.common;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.collect.andylibrary.R;

/**
 * 自定义Toast.
 */
public class ToastUtils {
    private static Context mContext;
    private volatile static ToastUtils mInstance;
    private static Toast mToast;
    private View layout;
    private TextView tv;
    private ImageView mImageView;
    private final int ERR = -1;
    private final int WARN = 0;
    private final int OK = 1;

    public ToastUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 单例模式
     *
     * @param context 传入的上下文
     * @return TabToast实例
     */
    private static ToastUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils(context);
                }
            }
        }
        return mInstance;
    }

    private static void getToast(int duration) {
        if (mToast == null) {
            mToast = new Toast(mContext);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(duration);
        }
    }

    public static void successToast(Context context, String text) {
        if (!StrUtils.isEmpty(text))
            textToast(context, text, 1, Toast.LENGTH_SHORT);
    }

    public static void warnToast(Context context, String text) {
        if (!StrUtils.isEmpty(text))
            textToast(context, text, 0, Toast.LENGTH_SHORT);
    }

    public static void errorToast(Context context, String text) {
        if (!StrUtils.isEmpty(text))
            textToast(context, text, -1, Toast.LENGTH_SHORT);
    }

    public static void textToast(Context context, String text, int stateId, int duration) {
        getInstance(context);
        getToast(duration);
        if (mInstance.layout == null || mInstance.tv == null) {
            mInstance.layout = LayoutInflater.from(mContext).inflate(R.layout.toast_layout, null);
            mInstance.tv = (TextView) mInstance.layout.findViewById(R.id.toast_text);
            mInstance.mImageView = (ImageView) mInstance.layout.findViewById(R.id.toast_image);
            mToast.setView(mInstance.layout);
        }
        mInstance.tv.setText(text);
        int resId = 0;
        if (stateId == -1)
            resId = R.mipmap.toast_error;
        else if (stateId == 0)
            resId = R.mipmap.toast_warn;
        else if (stateId == 1) {
            resId = R.mipmap.toast_success;
        }
        mInstance.mImageView.setImageResource(resId);
        mToast.show();
    }
}
