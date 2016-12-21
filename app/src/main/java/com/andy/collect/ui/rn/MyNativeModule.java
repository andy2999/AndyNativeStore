package com.andy.collect.ui.rn;

import android.content.Context;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * 这个类里面，放需要被RN调用的方法(NativeModule)
 *
 * @author andy he
 * @date 2016/11/30 20:54
 */
public class MyNativeModule extends ReactContextBaseJavaModule {

    private Context context;

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public boolean canOverrideExistingModule() {
        //return super.canOverrideExistingModule();
        return true;
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }

    //原生函数不能有返回值，调用是异步的，原生代码执行完成后只能通过函数回调或发送消息给rn那边
    @ReactMethod
    public void rnCallNative(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
