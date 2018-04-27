package com.betall.app.util;

import android.widget.Toast;

import com.betall.app.MyApplication;

/**
 * Created by fly on 2018/1/30.
 */

public final class ToastUtil {

    public static void showShort(String msg) {
        MyApplication app = MyApplication.shared();
        Toast.makeText(app, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        MyApplication app = MyApplication.shared();
        Toast.makeText(app, msg, Toast.LENGTH_LONG).show();
    }
}
