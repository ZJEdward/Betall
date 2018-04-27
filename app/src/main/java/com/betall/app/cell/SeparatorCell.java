package com.betall.app.cell;

import android.view.View;

import com.betall.app.R;

/**
 * Created by fly on 2018/1/31.
 */

public class SeparatorCell extends BaseCell {
    public SeparatorCell(View itemView) {
        super(itemView);

        itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.colorGroup));
    }

    public static int layoutId() {
        return R.layout.cell_separator;
    }
}
