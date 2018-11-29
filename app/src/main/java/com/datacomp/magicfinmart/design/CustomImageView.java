package com.datacomp.magicfinmart.design;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    /**
     * @param context
     */
    public CustomImageView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

    }

    /**
     * @param context
     * @param attrs
     */
    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int width = getMeasuredWidth() ;
        int height = (int)(getMeasuredWidth() *1.5);
        setMeasuredDimension(width, height);
    }
}