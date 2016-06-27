package com.andy.collect.andylibrary.common;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @author andy he
 * @ClassName: CommonUtils
 * @Description: 通用、不好归类的工具
 * @date 2016年1月15日 上午10:18:53
 */
public class CommonUtils {

    /**
     * desc: 网络是否可用
     *
     * @author andy he
     * @time 2016/5/4 12:49
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    //网络类型
    public static final int NETWORK_NONE = 0;//无连接
    public static final int NETWORK_OTHER = 1;//其它：非4g,非wifi
    public static final int NETWORK_4G = 4;//4g
    public static final int NETWORK_WIFI = 9;//wifi

    /**
     * desc: 网络类型（4G/WIFI/其它）
     *
     * @author andy he
     * @time 2016/5/4 12:49
     */
    public static int GetNetworkType(Context context) {
        int netType = NETWORK_NONE;//无网络
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    netType = NETWORK_WIFI;
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    int networkType = networkInfo.getSubtype();
                    switch (networkType) {
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            netType = NETWORK_4G;
                            break;
                        default:
                            netType = NETWORK_OTHER;
                            break;
                    }
                }
            }
        }
        return netType;
    }


    /**
     * 获取版本名称
     */
    public static String versionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     */
    public static int versionCode(Context context) {
        int currentVersionCode = 0;
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return currentVersionCode = info.versionCode; // 版本号
        } finally {
            return currentVersionCode;
        }
    }


    /**
     * 显示键盘
     *
     * @param context 内容上下文
     */
    public static void showKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏键盘
     *
     * @param view 控件
     */
    public static void hiddenKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    /**
     * 检查SDK是否存在
     *
     * @return
     */
    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取屏幕
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 像素转化
     */
    public static float dp2px(Context context,float dpVal){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
    /**
     * 像素转化
     */
    public static float px2dp(Context context,float pxVal){
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxVal, context.getResources().getDisplayMetrics());
    }

    public static String getUuid(Context context) {
        String m_szUniqueID = "";// create a hex string

        try {
            //1. The IMEI: 仅仅只对Android手机有效:
            TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String m_szImei = TelephonyMgr.getDeviceId();

            //2. Pseudo-Unique ID, 这个在任何Android手机中都有效
            String m_szDevIDShort = "23"
                    + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10
                    + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                    + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                    + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                    + Build.USER.length() % 10; //13 digits

            //3. The Android ID
            String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

            //4. The WLAN MAC Address string
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = "";
            if (wm!=null)
                m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

            //5. The BT MAC Address string
            BluetoothAdapter m_BluetoothAdapter = null;
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            String m_szBTMAC = m_BluetoothAdapter.getAddress();

            //集合前面5种唯一ID，拼接后计算出MD5值来产生一个结果（32位的16进制数据）
            String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID + m_szWLANMAC + m_szBTMAC;
            // compute md5
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
            byte p_md5Data[] = m.digest();// get md5 bytes

            for (int i = 0; i < p_md5Data.length; i++) {
                int b = (0xFF & p_md5Data[i]);
                if (b <= 0xF)// if it is a single digit, make sure it have 0 in front (proper padding)
                    m_szUniqueID += "0";
                m_szUniqueID += Integer.toHexString(b);// add number to string
            }
            m_szUniqueID = m_szUniqueID.toUpperCase(Locale.CHINESE);// hex string to uppercase
        } catch (Exception e) {
            m_szUniqueID="";
        }
        return m_szUniqueID;
    }

}
