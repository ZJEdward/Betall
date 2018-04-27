package com.betall.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.util.ArrayList;

/**
 * Created by fly on 2018/2/5.
 */

public class GestureView extends View {

    private static final int INNER_RADIUS = 20;
    private static final int OUTER_RADIUS = 40;
    private static final int LINE_WIDTH = 8;

    private ArrayList<Node> nodes;
    private ArrayList<Node> selectedNodes;
    private Point endPoint;
    private Paint paint;
    private Path linePath;

    private OnGestureUnlockListener onGestureUnlockListener;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.nodes = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            this.nodes.add(new Node(i));
        }

        this.selectedNodes = new ArrayList<>();
        this.endPoint = new Point();
        this.paint = new Paint();
        this.linePath = new Path();
    }

    public void setOnGestureUnlockListener(OnGestureUnlockListener onGestureUnlockListener) {
        this.onGestureUnlockListener = onGestureUnlockListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = this.getWidth();
        int spacing = width / 3;
        int innerRadius = QMUIDisplayHelper.dp2px(getContext(), INNER_RADIUS);
        int outerRadius = QMUIDisplayHelper.dp2px(getContext(), OUTER_RADIUS);

        for (int i = 0; i < this.nodes.size(); ++i) {
            Node node = this.nodes.get(i);
            node.centerX = spacing / 2 + (i % 3) * spacing;
            node.centerY = spacing / 2 + (i / 3) * spacing;
            node.outerRadius = outerRadius;
            node.innerRadius = innerRadius;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.paint.setStyle(Paint.Style.FILL);

        // 绘制初始 nodes
        this.paint.setColor(Color.DKGRAY);
        for (Node node : this.nodes) {
            canvas.drawCircle(node.centerX, node.centerY, node.innerRadius, paint);
        }

        if (this.selectedNodes.size() == 0)
            return;

        // 绘制内圆
        this.paint.setColor(Color.BLUE);
        for (Node node : this.selectedNodes) {
            canvas.drawCircle(node.centerX, node.centerY, node.innerRadius, paint);
        }

        // 绘制外圆
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(QMUIDisplayHelper.dp2px(getContext(), 4));
        for (Node node : this.selectedNodes) {
            canvas.drawCircle(node.centerX, node.centerY, node.outerRadius, paint);
        }

        // 绘制线
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(QMUIDisplayHelper.dp2px(getContext(), LINE_WIDTH));
        Node firstNode = this.selectedNodes.get(0);
        this.linePath.reset();
        this.linePath.moveTo(firstNode.centerX, firstNode.centerY);
        for (int i = 1; i < this.selectedNodes.size(); ++i) {
            Node node = this.selectedNodes.get(i);
            this.linePath.lineTo(node.centerX, node.centerY);
        }
        this.linePath.lineTo(this.endPoint.x, this.endPoint.y);
        canvas.drawPath(this.linePath, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.endPoint.x = (int) event.getX();
        this.endPoint.y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.selectedNodes.clear();
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < this.nodes.size(); ++i) {
                    Node node = this.nodes.get(i);
                    if (node.contains(endPoint)) {
                        if (!this.selectedNodes.contains(node)) {
                            this.selectedNodes.add(node);
                        }
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (this.selectedNodes.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Node node : this.selectedNodes) {
                        stringBuilder.append(String.valueOf(node.id));
                    }
                    if (this.onGestureUnlockListener != null) {
                        this.onGestureUnlockListener.onGestureFinish(stringBuilder.toString());
                    }
                    this.selectedNodes.clear();
                }
                break;
        }

        this.invalidate();
        return true;
    }

    private static class Node {
        private int centerX;
        private int centerY;
        private int innerRadius;
        private int outerRadius;
        private int id;

        private Node(int id) {
            this.id = id;
        }

        private boolean contains(Point point) {
            int left = centerX - outerRadius;
            int top = centerY - outerRadius;
            int right = centerX + outerRadius;
            int bottom = centerY + outerRadius;
            int px = point.x;
            int py = point.y;
            return px >= left && px <= right && py >= top && py <= bottom;
        }
    }

    public interface OnGestureUnlockListener {
        void onGestureFinish(String pwd);
    }
}
