package com.cretin.www.clearedittextproject.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cretin.www.clearedittextproject.R;

/**
 * Created by Cretin on 2017/8/8.
 */

public class ClearEditText extends LinearLayout {
    //记录当前自定义view的宽度
    private int mWidth;
    //记录当前自定义view的高度
    private int mHeight;
    private EditText mEdittext;
    private ImageView mImageView;
    private Context mContext;
    private float scaleSize;

    public ClearEditText(Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        setOrientation(HORIZONTAL);

        scaleSize = getScale(context);

        mEdittext = new EditText(mContext);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        mEdittext.setBackgroundColor(Color.WHITE);
        mEdittext.setPadding(( int ) (10 * scaleSize), ( int ) (4 * scaleSize),
                ( int ) (10 * scaleSize), ( int ) (4 * scaleSize));
        addView(mEdittext, layoutParams);

        mImageView = new ImageView(mContext);
        mImageView.setImageResource(R.mipmap.clear);
        LayoutParams layoutParamsImage = new
                LayoutParams(( int ) (30 * scaleSize), ( int ) (30 * scaleSize));
        mImageView.setPadding(5, 5, 5, 5);
        mImageView.setVisibility(INVISIBLE);
        addView(mImageView, layoutParamsImage);

        setGravity(Gravity.CENTER);

        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mEdittext.getEditableText().clear();
            }
        });

        mEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ( !TextUtils.isEmpty(charSequence) ) {
                    mImageView.setVisibility(VISIBLE);
                } else {
                    mImageView.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public EditText getmEdittext() {
        return mEdittext;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    /**
     * 获取屏幕缩放比
     *
     * @param context
     * @return
     */
    private float getScale(Context context) {
        TextView tv = new TextView(context);
        tv.setTextSize(1);
        return tv.getTextSize();
    }
}
