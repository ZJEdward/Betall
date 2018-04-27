package com.betall.app.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.betall.app.R;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fly on 2018/1/29.
 */

public class RootFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabBar)
    QMUITabSegment tabBar;

    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected View onCreateView() {

        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_root, null);

        ButterKnife.bind(this, view);

        this.initTabBar();
        this.initViewPagers();

        return view;
    }

    private void initTabBar() {
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        tabBar.setDefaultNormalColor(normalColor);
        tabBar.setDefaultSelectedColor(selectColor);
        QMUITabSegment.Tab homeTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component_selected),
                "首页", false
        );

        QMUITabSegment.Tab demoTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util),
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util_selected),
                "演示", false
        );
        QMUITabSegment.Tab meTab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab),
                ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab_selected),
                "我的", false
        );
        tabBar.addTab(homeTab)
                .addTab(demoTab)
                .addTab(meTab);
        tabBar.notifyDataChanged();
    }

    private void initViewPagers() {
        HomeFragment homeFragment = new HomeFragment();
        DemoFragment demoFragment = new DemoFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(demoFragment);
        fragments.add(meFragment);
        this.viewPager.setAdapter(this.createAdapter());

        // 联动
        tabBar.setupWithViewPager(viewPager, false);
    }

    private FragmentPagerAdapter createAdapter() {
        return new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
