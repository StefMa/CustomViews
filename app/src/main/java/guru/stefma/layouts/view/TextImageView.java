package guru.stefma.layouts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import guru.stefma.layouts.R;

public class TextImageView extends ViewGroup {

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(getContext(), R.layout.text_image_layout, this);
    }

    // Called first.
    // Get Child Count an measure each.
    // After that we set the measure of our own TextImageView with
    // "match_parent" as width and the height the same like child "1".
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        setMeasuredDimension(widthMeasureSpec, getChildAt(1).getMeasuredHeight());
    }

    // Called second.
    // Get each view ("last to first") and set it to our TextImageView
    // First we get all Images and set it.
    // After that we set our TextView width the rest with which is available in our TextImageView
    // and in the middle of our full TextImageView height.
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int rightOfLastView = getRight();
        for (int i = getChildCount() - 1; i > 0; i--) {
            View child = getChildAt(i);
            child.layout(rightOfLastView - child.getMeasuredWidth(), 0, rightOfLastView, getMeasuredHeight());
            rightOfLastView -= child.getMeasuredWidth();
        }

        View child = getChildAt(0);
        int textImageLayoutHeight = getMeasuredHeight();
        int topOfTextLabel = (textImageLayoutHeight / 2) - (child.getMeasuredHeight() / 2);
        child.layout(0, topOfTextLabel, rightOfLastView, topOfTextLabel + child.getMeasuredHeight());
    }
}
