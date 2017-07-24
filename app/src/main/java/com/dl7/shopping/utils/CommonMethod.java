package com.dl7.shopping.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by MC.Zeng on 2017-07-13.
 */

public class CommonMethod {

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view
     */
    public static void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param view
     */
    public static void showInput(Context context, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    /*
   * 获取当前程序的版本号
   */
    public static String getVersionName(Context context) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 获取uid
     *
     * @param context
     * @return
     */
    public static String getUid(Context context) {
        SharedPreferences sp = context.getSharedPreferences("flag", context.MODE_PRIVATE);
        return sp.getString("data", "0");
    }

}
