package com.betall.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betall.app.cell.BaseCell;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fly on 2018/1/31.
 */

public class BaseListAdapter extends RecyclerView.Adapter<BaseCell> {

    @Override
    public BaseCell onCreateViewHolder(ViewGroup parent, int viewType) {

        Class<? extends BaseCell> cellClass = this.baseListListener.cellClass();
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

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutId, parent, false);

        BaseCell cell = null;
        try {
            Constructor<? extends BaseCell> constructor = cellClass.getConstructor(View.class);
            cell = constructor.newInstance(view);
        }
        catch (NoSuchMethodException | IllegalAccessException | java.lang.InstantiationException | InvocationTargetException e) {
            RuntimeException re = new RuntimeException("cell class not found");
            re.initCause(e);
            throw re;
        }
        BaseCell finalCell = cell;
        cell.itemView.setOnClickListener(v -> {
            if (this.baseListListener != null)
                this.baseListListener.onClick(finalCell, finalCell.getAdapterPosition());
        });
        return cell;
    }

    @Override
    public void onBindViewHolder(BaseCell holder, int position) {
        if (this.baseListListener != null)
            this.baseListListener.onBind(holder, position);
    }

    @Override
    public int getItemCount() {
        if (this.baseListListener != null)
            return this.baseListListener.itemCount();
        return 0;
    }

    /******* BaseListListener ******/

    public interface BaseListListener {

        // 在这里处理点击事件
        void onClick(BaseCell cell, int row);

        // 在这里配置cell
        void onBind(BaseCell cell, int row);

        // 返回行数
        int itemCount();

        // 返回cell的class
        Class<? extends BaseCell> cellClass();
    }

    private BaseListListener baseListListener;

    public void setBaseListListener(BaseListListener baseListListener) {
        this.baseListListener = baseListListener;
    }
}
