package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.betall.app.cell.DemoCell;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by fly on 2018/1/29.
 */

public class MeFragment extends BaseStaticListFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QMUITopBar titleBar = getTitleBar();
        titleBar.setTitle("我的");
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

    @Override
    protected void onLoadCells() {

        DemoCell demoCell = addStaticCell(DemoCell.class);
        demoCell.titleLabel.setText("哈哈哈");
        demoCell.subtitleLabel.setText("娃哈哈");
        demoCell.setOnClickListener(() -> {
            Log.e(getClass().getSimpleName(), "onLoadCells: " );
        });

        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);

        beginGroup();

        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);
        addStaticCell(DemoCell.class);
    }
}
