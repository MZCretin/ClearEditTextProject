package com.cretin.www.clearedittext.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.cretin.www.clearedittext.R;


/**
 * Created by cretin on 2017/8/11.
 */

public class ClearEditText extends EditText {
    private static final float DEFAUT_SCALE = 0.5f;
    private Bitmap mClearBitmap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private boolean showClose;
    private float scale;
    private float padding;

    public ClearEditText(Context context) {
        super(context);
        init(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int clearIcon = 0;
        if ( attrs != null ) {
            //获得这个控件对应的属性。
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ClearEditText);
            try {
                //获得属性值
                clearIcon = a.getResourceId(R.styleable.ClearEditText_clearIcon, 0);
                scale = a.getResourceId(R.styleable.ClearEditText_scaleSize, 0);
            } finally { //回收这个对象
                a.recycle();
            }
        }
        //设置删除图标
        if ( clearIcon != 0 ) {
            mClearBitmap = BitmapFactory.decodeResource(getResources(), clearIcon);
        } else
            mClearBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.delete);

        if ( scale == 0 ) {
            scale = DEFAUT_SCALE;
        }

        mPaint = new Paint();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClose = !TextUtils.isEmpty(s);
                invalidate();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_UP ) {
            //在图标的范围内点击有效
            if ( event.getX() > (getWidth() - getHeight() + padding)
                    && event.getX() < (getWidth() - padding)
                    && event.getY() > padding
                    && event.getY() < (getHeight() - padding) ) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ( showClose ) {
            Rect mSrcRect = new Rect(0, 0, mHeight, mHeight);
            RectF mDestRect = new RectF(mWidth - mHeight + padding,
                    padding, mWidth - padding, mHeight - padding);
            canvas.drawBitmap(mClearBitmap, mSrcRect, mDestRect, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if ( widthMode == MeasureSpec.EXACTLY ) {
            //Must be this size
            width = widthSize;
        } else if ( widthMode == MeasureSpec.AT_MOST ) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if ( heightMode == MeasureSpec.EXACTLY ) {
            //Must be this size
            height = heightSize;
        } else if ( heightMode == MeasureSpec.AT_MOST ) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        mWidth = width;
        mHeight = height;
        //MUST CALL THIS

        //计算偏移量
        padding = (( float ) mHeight) * (1 - scale) / 2;
        setMeasuredDimension(width, height);
    }
}
