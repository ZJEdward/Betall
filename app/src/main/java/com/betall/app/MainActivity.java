package com.betall.app;

import android.os.Bundle;
import android.util.Log;

import com.betall.app.api.Api;
import com.betall.app.api.ResponseObserver;
import com.betall.app.bean.IpInfo;
import com.betall.app.fragment.RootFragment;
import com.betall.app.util.UserSettings;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;


public class MainActivity extends QMUIFragmentActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected int getContextViewId() {
        return 100;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 启动 root fragment
        startFragment(new RootFragment());
//        startFragment(new GestureFragment());

        // API用法演示
        this.apiDemo();

        UserSettings.shared("fly").setMobile("18936588338");
    }

    private void apiDemo() {
        Api.getDefault().getIpInfo("63.223.108.42")
                .subscribe(new ResponseObserver<IpInfo>(data -> {
                    Log.e(TAG, "onCreate: GET: " + data.cityId);
                }));

        Api.getDefault().postInInfo("63.223.108.42")
                .subscribe(new ResponseObserver<IpInfo>(data -> {
                    Log.e(TAG, "onCreate: POST: " + data.cityId);
                }));
    }
}
