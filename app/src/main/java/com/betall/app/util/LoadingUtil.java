package com.betall.app.util;


import android.content.Context;
import android.os.Handler;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * Created by fly on 2018/2/2.
 */

public final class LoadingUtil {

    private QMUITipDialog dialog;

    private static LoadingUtil instance = new LoadingUtil();

    private LoadingUtil() {

    }

    public static LoadingUtil shared() {
        return instance;
    }


    public void showLoading(Context context, String msg) {
        this.dismiss();
        this.dialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg)
                .create();
        this.dialog.show();
    }

    public void showSuccess(Context context, String msg) {
        this.dismiss();
        this.dialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(msg)
                .create();
        this.dialog.show();
        runDelayed(1000, this::dismiss);
    }

    public void showFail(Context context, String msg) {
        this.dismiss();
        this.dialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(msg)
                .create();
        this.dialog.show();
        runDelayed(1000, this::dismiss);
    }

    public void showInfo(Context context, String msg) {
        this.dismiss();
        this.dialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord(msg)
                .create();
        this.dialog.show();
        runDelayed(1000, this::dismiss);
    }

    public void dismiss() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
            this.dialog = null;
        }
    }

    public static void runDelayed(int delay, Runnable runnable) {
        new Handler().postDelayed(runnable, delay);
    }
}
