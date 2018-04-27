package com.betall.app.cell;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.betall.app.R;

import butterknife.BindView;

/**
 * Created by fly on 2018/2/2.
 */

public class KeyTextEditCell extends BaseCell {

    @BindView(R.id.keyLabel)
    public TextView keyLabel;

    @BindView(R.id.textEditView)
    public EditText textEditView;

    public KeyTextEditCell(View itemView) {
        super(itemView);

        itemView.setBackground(null);
    }

    public static int layoutId() {
        return R.layout.cell_key_text_edit;
    }
}
