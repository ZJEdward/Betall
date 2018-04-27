package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.betall.app.cell.BaseCell;
import com.betall.app.cell.ButtonCell;
import com.betall.app.cell.DemoCell;
import com.betall.app.cell.IconKeyCell;
import com.betall.app.cell.KeyTextEditCell;
import com.betall.app.util.LoadingUtil;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by fly on 2018/1/29.
 */

public class HomeFragment extends BaseStaticListFragment {

    private static final String TAG = "HomeFragment";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QMUITopBar titleBar = getTitleBar();
        titleBar.setTitle("首页");
    }

    @Override
    protected void onLoadCells() {

        beginGroup();

        DemoCell demoCell = addStaticCell(DemoCell.class);
        demoCell.titleLabel.setText("哈哈哈");
        demoCell.subtitleLabel.setText("娃哈哈");
        demoCell.setOnClickListener(() -> {
            Log.e(getClass().getSimpleName(), "onLoadCells: ");
        });

        IconKeyCell iconKeyCell = addStaticCell(IconKeyCell.class);
        iconKeyCell.keyLabel.setText("设置");

        beginGroup();

        KeyTextEditCell keyTextEditCell = addStaticCell(KeyTextEditCell.class);
        keyTextEditCell.keyLabel.setText("用户名");
        keyTextEditCell.textEditView.setHint("请输入用户名");

        KeyTextEditCell pwdTextEditCell = addStaticCell(KeyTextEditCell.class);
        pwdTextEditCell.keyLabel.setText("用户密码");
        pwdTextEditCell.textEditView.setHint("请输入用户密码");

        beginGroup();

        addStaticCell(DemoCell.class);

        beginGroup(BaseCell.SeparatorStyle.NONE);

        ButtonCell buttonCell = addStaticCell(ButtonCell.class);
        buttonCell.button.setText("完成");
        buttonCell.button.setOnClickListener(button -> {
            LoadingUtil.shared().showSuccess(getContext(), "成功");
        });
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
