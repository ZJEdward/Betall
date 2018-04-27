package com.betall.app.cell;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.betall.app.R;

import butterknife.BindView;

/**
 * Created by fly on 2018/2/2.
 */

public class IconKeyCell extends BaseCell {

    @BindView(R.id.iconView)
    public ImageView iconView;

    @BindView(R.id.keyLabel)
    public TextView keyLabel;

    public IconKeyCell(View itemView) {
        super(itemView);
    }

    public static int layoutId() {
        return R.layout.cell_icon_key;
    }
}
