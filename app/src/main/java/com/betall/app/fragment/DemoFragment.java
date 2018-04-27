package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.betall.app.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;

/**
 * Created by fly on 2018/1/29.
 */

public class DemoFragment extends BaseTitleBarFragment {

    @BindView(R.id.button) Button button;

    @Override
    protected View onCreateContentView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_demo, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        QMUITopBar titleBar = getTitleBar();
        titleBar.setTitle("演示");

        this.button.setOnClickListener(sender -> {
            DemoListFragment fragment = new DemoListFragment();
            this.push(fragment);
        });
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
