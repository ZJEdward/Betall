package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.betall.app.cell.BaseCell;
import com.betall.app.cell.DemoCell;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by fly on 2018/1/30.
 */

public class DemoListFragment extends BaseListRefreshFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QMUITopBar titleBar = this.getTitleBar();
        titleBar.setTitle("列表详情");
        titleBar.addLeftBackImageButton().setOnClickListener(sender -> { popBackStack(); });
    }

    @Override
    public void onClick(BaseCell cell, int row) {
        Log.e(getClass().getSimpleName(), "onClick: " + row);
    }

    @Override
    public void onBind(BaseCell cell, int row) {
        DemoCell demoCell = (DemoCell) cell;
        demoCell.titleLabel.setText("row: " + row);
    }

    @Override
    public int itemCount() {

        return 20;
    }

    @Override
    public Class<? extends BaseCell> cellClass() {
        return DemoCell.class;
    }
}
