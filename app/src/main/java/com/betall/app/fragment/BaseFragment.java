package com.betall.app.fragment;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * Created by fly on 2018/1/29.
 */

public abstract class BaseFragment extends QMUIFragment {

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    public void push(BaseFragment fragment) {
        this.startFragment(fragment);
    }
}
