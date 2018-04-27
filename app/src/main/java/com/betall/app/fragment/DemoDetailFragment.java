package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by fly on 2018/1/30.
 */

public class DemoDetailFragment extends BaseTitleBarFragment {

    @Override
    protected View onCreateView() {
        // TODO
        return super.onCreateView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QMUITopBar titleBar = this.getTitleBar();
        titleBar.setTitle("详情");
        titleBar.addLeftBackImageButton().setOnClickListener(sender -> { popBackStack(); });
    }
}
