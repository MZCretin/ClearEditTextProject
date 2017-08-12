package com.cretin.www.clearedittextproject.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.cretin.www.clearedittextproject.R;

/**
 * Created by cretin on 2017/8/11.
 */

public class ClearEditText1 extends EditText {
    private Drawable mClearDrawable;
    private int mWidth;
    private int mHeight;

    public ClearEditText1(Context context) {
        super(context);
        init(context);
    }

    public ClearEditText1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClearEditText1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //实例化右边的清除图片 如果要投入使用最好不要写死，需要后续封装，通过自定义属性设置
        mClearDrawable = context.getResources().getDrawable(R.mipmap.delete);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
    }

    //设置删除图片
    private void setDrawable() {
        if ( length() < 1 )
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        else {
            mClearDrawable.setBounds(0, 0, mHeight / 2, mHeight / 2);
            setCompoundDrawables(null, null, mClearDrawable, null);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_UP ) {
            if ( event.getX() > getWidth()
                    - getPaddingRight()
                    - mClearDrawable.getIntrinsicWidth() ) {
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

//        如果要投入使用最好不要写死，需要后续封装，通过自定义属性设置
        setPadding(10, 0, mHeight / 4, 10);
        setDrawable();
        setMeasuredDimension(width, height);
    }
}
