package com.madreain.sku.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.madreain.sku.utils.DisplayUtil;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：解决Sku过多时，选择界面铺满全屏的问题
 */
public class SkuMaxHeightScrollView extends ScrollView {

    public SkuMaxHeightScrollView(Context context) {
        super(context);
    }

    public SkuMaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        if (heightDp > 220) {
            int maxHeight = DisplayUtil.dp2px(getContext(), 220);
            setMeasuredDimension(width, maxHeight);
        } else if (heightDp < 75) {
            int minHeight = DisplayUtil.dp2px(getContext(), 75);
            setMeasuredDimension(width, minHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
