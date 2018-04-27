package com.betall.app.cell;

import android.view.View;
import android.widget.Button;

import com.betall.app.R;

import butterknife.BindView;

/**
 * Created by fly on 2018/2/2.
 */

public class ButtonCell extends BaseCell {

    @BindView(R.id.button)
    public Button button;

    public ButtonCell(View itemView) {
        super(itemView);

        this.separatorStyle = SeparatorStyle.NONE;

        int bgColor = itemView.getContext().getResources().getColor(R.color.colorBackground);
        itemView.setBackgroundColor(bgColor);
    }

    public static int layoutId() {
        return R.layout.cell_button;
    }
}
