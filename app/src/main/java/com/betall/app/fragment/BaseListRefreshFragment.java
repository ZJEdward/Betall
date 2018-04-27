package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.betall.app.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created by fly on 2018/1/31.
 */

public abstract class BaseListRefreshFragment extends BaseListFragment implements OnRefreshListener, OnLoadmoreListener {

    @BindView(R.id.refreshControl)
    SmartRefreshLayout refreshControl;

    @Override
    protected View onCreateContentView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_base_list_refresh, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.refreshControl.setOnRefreshListener(this);
        this.refreshControl.setOnLoadmoreListener(this);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshControl.finishRefresh(3000);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshControl.finishLoadmore(3000);
    }
}
