package guru.stefma.layouts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class RightToLeftLayout extends ViewGroup {

    public RightToLeftLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightOfHighestChild = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (heightOfHighestChild < child.getMeasuredHeight()) {
                heightOfHighestChild = child.getMeasuredHeight();
            }
        }
        setMeasuredDimension(widthMeasureSpec, heightOfHighestChild);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int rightOfLastView = getRight();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(rightOfLastView - child.getMeasuredWidth(), 0, rightOfLastView, getMeasuredHeight());
            rightOfLastView -= child.getMeasuredWidth();
        }
    }
}
