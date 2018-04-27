package com.betall.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.betall.app.R;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.ButterKnife;

/**
 * Created by fly on 2018/1/30.
 */

// 带标题栏的页面
public class BaseTitleBarFragment extends BaseFragment {

    private QMUITopBar titleBar;
    private View contentView;

    public QMUITopBar getTitleBar() {
        return titleBar;
    }

    public View getContentView() {
        return contentView;
    }

    @Override
    protected View onCreateView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_base_title_bar, null);

        this.titleBar = rootView.findViewById(R.id.titleBar);
        this.contentView = onCreateContentView();

        if (this.contentView != null) {
            FrameLayout frameLayout = rootView.findViewById(R.id.containerView);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            frameLayout.addView(contentView, layoutParams);
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (this.contentView != null)
            ButterKnife.bind(this, this.contentView);
    }

    // 子类实现
    protected View onCreateContentView() {
        return null;
    }
}
