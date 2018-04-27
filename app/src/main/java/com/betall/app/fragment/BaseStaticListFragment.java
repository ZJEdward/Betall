package com.betall.app.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.betall.app.R;
import com.betall.app.cell.BaseCell;
import com.betall.app.cell.SeparatorCell;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fly on 2018/2/1.
 */

public abstract class BaseStaticListFragment extends BaseTitleBarFragment {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    private LayoutInflater layoutInflater;
    private List<List<BaseCell>> groupList = new ArrayList<>();

    @Override
    protected View onCreateContentView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_base_static_list, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.linearLayout.setBackgroundColor(Color.WHITE);

        this.layoutInflater = LayoutInflater.from(getContext());

        this.reloadCells();
    }

    public void reloadCells() {
        this.groupList.clear();
        this.linearLayout.removeAllViews();
        this.onLoadCells();
        this.doAddCells();
    }

    protected abstract void onLoadCells();

    private void doAddCells() {
        for (int i = 0; i < this.groupList.size(); ++i) {
            List<BaseCell> cellList = this.groupList.get(i);
            for (int j = 0; j < cellList.size(); ++j) {
                BaseCell cell = cellList.get(j);
                this.linearLayout.addView(cell.itemView);

                if (cell.separatorStyle == BaseCell.SeparatorStyle.DEFAULT) {
                    if (j == 0 || j == cellList.size() - 1) {
                        this.addCellSeparator(BaseCell.SeparatorStyle.LINE);
                    }
                    else {
                        this.addCellSeparator(BaseCell.SeparatorStyle.INDENT);
                    }
                }
                else {
                    this.addCellSeparator(cell.separatorStyle);
                }
            }
        }
    }

    public <T extends BaseCell> T addStaticCell(Class<T> cellClass) {
        return addStaticCell(cellClass, -1, BaseCell.SeparatorStyle.DEFAULT);
    }

    public <T extends BaseCell> T addStaticCell(Class<T> cellClass, int height) {
        return addStaticCell(cellClass, height, BaseCell.SeparatorStyle.DEFAULT);
    }

    public <T extends BaseCell> T addStaticCell(Class<T> cellClass, BaseCell.SeparatorStyle separatorStyle) {
        return addStaticCell(cellClass, -1, separatorStyle);
    }

    public <T extends BaseCell> T addStaticCell(Class<T> cellClass, int height, BaseCell.SeparatorStyle separatorStyle) {
        int layoutId;
        try {
            Method method = cellClass.getMethod("layoutId");
            layoutId = (Integer) method.invoke(null);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            RuntimeException re = new RuntimeException( cellClass.getName() + " layoutId method not found");
            re.initCause(e);
            throw re;
        }

        View itemView = this.layoutInflater.inflate(layoutId, this.linearLayout, false);
        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        if (layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT &&
                height > -1)
            layoutParams.height = QMUIDisplayHelper.dp2px(getContext(), height);
        itemView.setLayoutParams(layoutParams);

        T cell = null;
        try {
            Constructor<T> constructor = cellClass.getConstructor(View.class);
            cell = constructor.newInstance(itemView);
            if (cell.separatorStyle == BaseCell.SeparatorStyle.DEFAULT)
                cell.separatorStyle = separatorStyle;
        }
        catch (NoSuchMethodException | IllegalAccessException | java.lang.InstantiationException | InvocationTargetException e) {
            RuntimeException re = new RuntimeException("cell class not found");
            re.initCause(e);
            throw re;
        }

        // 确保不为空
        if (this.groupList.isEmpty())
            beginGroup(0);

        // 获取最后一个cellList
        List<BaseCell> cellList = this.groupList.get(this.groupList.size() - 1);
        cellList.add(cell);
        itemView.setOnClickListener(cell);
        return cell;
    }

    public void beginGroup() {
        beginGroup(15);
    }

    public void beginGroup(int height) {
        beginGroup(height, BaseCell.SeparatorStyle.DEFAULT);
    }

    public void beginGroup(BaseCell.SeparatorStyle style) {
        beginGroup(15, style);
    }

    public void beginGroup(int height, BaseCell.SeparatorStyle style) {
        this.groupList.add(new ArrayList<>());
        addStaticCell(SeparatorCell.class, height, style);
    }

    private void addCellSeparator(BaseCell.SeparatorStyle style) {
        switch (style) {
            case LINE:
                addCellSeparator(0);
                break;
            case INDENT:
                addCellSeparator(15);
                break;
            case NONE:
                break;
        }
    }

    // 分隔线直接加一个布局
    private void addCellSeparator(int leftMargin) {
        leftMargin = QMUIDisplayHelper.dp2px(getContext(), leftMargin);
        int height = QMUIDisplayHelper.dp2px(getContext(), 1);
        View itemView = this.layoutInflater.inflate(R.layout.cell_separator, this.linearLayout, false);
        itemView.setBackgroundColor(getResources().getColor(R.color.colorDivider));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        layoutParams.leftMargin = leftMargin;
        itemView.setLayoutParams(layoutParams);
        this.linearLayout.addView(itemView);
    }
}
