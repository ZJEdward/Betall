package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.betall.app.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created by fly on 2018/2/1.
 */

public abstract class BaseStaticListRefreshFragment extends BaseStaticListFragment implements OnRefreshListener {

    @BindView(R.id.refreshControl)
    SmartRefreshLayout refreshControl;

    @Override
    protected View onCreateContentView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_base_static_list_refresh, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.refreshControl.setEnableLoadmore(false);
        this.refreshControl.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshControl.finishRefresh(3000);
    }
}
