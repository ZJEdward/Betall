package com.betall.app.decroation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fly on 2018/1/31.
 */

public class BaseListItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight = 2;
    private int dividerColor = Color.GRAY;
    private int dividerIndent = 30;
    private Paint paint = new Paint();

    public void setDividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        this.paint.setColor(dividerColor);
    }

    public void setDividerIndent(int dividerIndent) {
        this.dividerIndent = dividerIndent;
    }

    public BaseListItemDecoration() {
        super();

        this.paint.setColor(dividerColor);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int lastPosition = state.getItemCount() - 1;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(child);
            drawDivider(c, child, pos == lastPosition);
        }
    }

    private void drawDivider(Canvas c, View child, boolean isLast) {

        int right = child.getWidth();
        float top = child.getBottom();
        float bottom = child.getBottom() + this.dividerHeight;
        if (isLast)
            c.drawRect(0, top, right, bottom, this.paint);
        else
            c.drawRect(this.dividerIndent, top, right, bottom, this.paint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = this.dividerHeight;
    }
}
