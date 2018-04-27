package com.betall.app.util;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.betall.app.R;

/**
 * Created by fly on 2018/2/3.
 */

public final class AlertUtil {
    public static void show(Context context, String title, String msg, String posButtonTitle) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton("取消", null)
                .setPositiveButton(posButtonTitle, null)
                .setCancelable(false)
                .create();
        dialog.show();
    }

    public static void showCustomView(Context context, String title, String msg, View.OnClickListener onClickListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.alert_view, null);
        TextView titleLabel = view.findViewById(R.id.titleLabel);
        TextView msgLabel = view.findViewById(R.id.msgLabel);
        Button cancelButton = view.findViewById(R.id.cancelButton);
        Button okButton = view.findViewById(R.id.okButton);
        titleLabel.setText(title);
        msgLabel.setText(msg);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();

        cancelButton.setOnClickListener(sender -> dialog.dismiss());
        okButton.setOnClickListener(sender -> {
            if (onClickListener != null)
                onClickListener.onClick(sender);
            dialog.dismiss();
        });
    }
}
