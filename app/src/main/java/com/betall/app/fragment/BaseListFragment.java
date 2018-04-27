package com.betall.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.betall.app.R;
import com.betall.app.adapter.BaseListAdapter;
import com.betall.app.cell.BaseCell;
import com.betall.app.decroation.BaseListItemDecoration;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;

/**
 * Created by fly on 2018/1/30.
 */

public abstract class BaseListFragment extends BaseTitleBarFragment implements BaseListAdapter.BaseListListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BaseListAdapter adapter;

    @Override
    protected View onCreateContentView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_base_list, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.recyclerView.setBackgroundColor(Color.WHITE);

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerView.setLayoutManager(layoutManager);

        // 设置分隔线
        BaseListItemDecoration decoration = new BaseListItemDecoration();
        decoration.setDividerColor(getResources().getColor(R.color.colorDivider));
        decoration.setDividerHeight(QMUIDisplayHelper.dp2px(getContext(), 1));
        decoration.setDividerIndent(QMUIDisplayHelper.dp2px(getContext(), 15));
        this.recyclerView.addItemDecoration(decoration);

        // 设置适配器
        this.adapter = new BaseListAdapter();
        this.adapter.setBaseListListener(this);
        this.recyclerView.setAdapter(this.adapter);
    }

    public void reloadCells() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public abstract void onClick(BaseCell cell, int row);

    @Override
    public abstract void onBind(BaseCell cell, int row);

    @Override
    public abstract int itemCount();

    @Override
    public abstract Class<? extends BaseCell> cellClass();
}
