package com.betall.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.betall.app.MyApplication;

/**
 * Created by fly on 2018/2/3.
 */

public final class UserSettings {

    private static UserSettings instance;
    private static String currentUserId;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static UserSettings shared() {
        return shared(null);
    }

    public static UserSettings shared(String userId) {
        if (TextUtils.isEmpty(userId))
            userId = "default";

        if (instance == null || !currentUserId.equals(userId)) {
            currentUserId = userId;
            instance = new UserSettings();
        }
        return instance;
    }

    private UserSettings() {
        MyApplication app = MyApplication.shared();
        this.preferences = app.getSharedPreferences("UserSettings_" + currentUserId, Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
        this.init();
    }

    /*********************************************/

    private void init() {
        this.mobile = this.preferences.getString("mobile", "");
    }

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        this.editor.putString("mobile", mobile);
        this.editor.apply();
    }
}
