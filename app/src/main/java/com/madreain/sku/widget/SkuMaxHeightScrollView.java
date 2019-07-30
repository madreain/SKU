package com.madreain.sku.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.madreain.sku.R;
import com.madreain.sku.utils.DisplayUtil;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：解决Sku过多时，选择界面铺满全屏的问题
 */
public class SkuMaxHeightScrollView extends ScrollView {

    private int maxHeight;
    private int minHeight;

    public SkuMaxHeightScrollView(Context context) {
        this(context, null);
    }

    public SkuMaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SkuMaxHeightScrollView, 0, 0);
        try {
            maxHeight = typedArray.getInt(R.styleable.SkuMaxHeightScrollView_maxSkuHeight, 220);
            minHeight = typedArray.getInt(R.styleable.SkuMaxHeightScrollView_minSkuHeight, 75);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }
        float heightDp = DisplayUtil.px2dp(getContext(), height);
        int skumaxHeight = DisplayUtil.dp2px(getContext(), maxHeight);
        int skuminHeight = DisplayUtil.dp2px(getContext(), minHeight);
        if (heightDp > skumaxHeight) {
            setMeasuredDimension(width, skumaxHeight);
        } else if (heightDp < skuminHeight) {
            setMeasuredDimension(width, skuminHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
