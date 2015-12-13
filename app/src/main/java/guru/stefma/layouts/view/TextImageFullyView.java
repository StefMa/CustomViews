package guru.stefma.layouts.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TextImageFullyView extends View implements View.OnTouchListener {

    private final Paint mTextPaint;
    private final Bitmap bitmap;

    public TextImageFullyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextSize(80);

        bitmap = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_input_add);

        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("Hello :)", 0, getMeasuredHeight() / 2 + 20, mTextPaint);
        canvas.drawBitmap(bitmap, getRight() - bitmap.getWidth(), getMeasuredHeight() / 2 - bitmap.getHeight() / 2, null);
        canvas.drawBitmap(bitmap, getRight() - bitmap.getWidth() * 2, getMeasuredHeight() / 2 - bitmap.getHeight() / 2, null);
        canvas.drawBitmap(bitmap, getRight() - bitmap.getWidth() * 3, getMeasuredHeight() / 2 - bitmap.getHeight() / 2, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, 120);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getX(0) >= 0 && event.getX(0) <= getMeasuredWidth() - bitmap.getWidth() * 3) {
            Log.e("Touched", "Text ");
        } else if (event.getX(0) <= getRight() && event.getX(0) >= getRight() - bitmap.getWidth()) {
            Log.e("Touched", "Last bitmap");
        }
        return false;
    }
}
