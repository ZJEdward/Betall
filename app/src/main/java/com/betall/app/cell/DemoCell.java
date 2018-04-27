package com.betall.app.cell;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.betall.app.R;

import butterknife.BindView;

/**
 * Created by fly on 2018/1/30.
 */


public class DemoCell extends BaseCell {

    @BindView(R.id.imageView)
    public ImageView imageView;

    @BindView(R.id.titleLabel)
    public TextView titleLabel;

    @BindView(R.id.subtitleLabel)
    public TextView subtitleLabel;

    public DemoCell(View itemView) {
        super(itemView);

        itemView.setBackground(null);
    }

    public static int layoutId() {
        return R.layout.cell_demo;
    }
}
