package guru.stefma.layouts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class IntroLayout extends ViewGroup {

    public IntroLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int layoutCenterWidth = getWidth() / 2;
        int layoutCenterHeight = getHeight() / 2;

        View android = getChildAt(1);
        int androidMeasuredWidth = android.getMeasuredWidth();
        int androidMeasuredHeight = android.getMeasuredHeight();
        int androidLeft = layoutCenterWidth - (androidMeasuredWidth / 2);
        int androidTop = layoutCenterHeight - (androidMeasuredHeight/2);
        int androidRight = layoutCenterWidth + (androidMeasuredWidth / 2);
        int androidBottom = layoutCenterHeight + (androidMeasuredHeight/ 2);
        android.layout(androidLeft, androidTop, androidRight, androidBottom);

        View line = getChildAt(0);
        int lineMeasuredWidth = line.getMeasuredWidth();
        int lineMeasuredHeight = line.getMeasuredHeight();
        int lineLeft = layoutCenterWidth - (lineMeasuredWidth / 2);
        int lineRight = layoutCenterWidth + (lineMeasuredWidth / 2);
        int lineBottom = androidBottom;
        int lineTop = lineBottom - lineMeasuredHeight;
        line.layout(lineLeft, lineTop, lineRight, lineBottom);
    }
}
