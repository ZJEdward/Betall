package com.betall.app.cell;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betall.app.R;

import butterknife.ButterKnife;

/**
 * Created by fly on 2018/1/30.
 */

public class BaseCell extends RecyclerView.ViewHolder implements View.OnClickListener {

    public enum SeparatorStyle {
        LINE,
        INDENT,
        NONE,
        DEFAULT
    }

    public SeparatorStyle separatorStyle = SeparatorStyle.DEFAULT;

    public BaseCell(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        // cell 的默认背景，子类可更改
        itemView.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.sel_cell));
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if (this.onClickListener != null)
            this.onClickListener.call();
    }

    public interface OnClickListener {
        void call();
    }

    public static int layoutId() {
        return R.layout.cell_base;
    }
}
