package com.madreain.sku.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.madreain.sku.R;
import com.madreain.sku.utils.DisplayUtil;


/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */

@SuppressLint("AppCompatCustomView")
public class SkuItemView extends TextView {
    private String attributeValue;

    public SkuItemView(Context context) {
        super(context);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.sku_item_selector);
        setTextColor(getResources().getColorStateList(R.color.sku_item_text_selector));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        setSingleLine();
        setGravity(Gravity.CENTER);
        setPadding(DisplayUtil.dp2px(context, 10), 0, DisplayUtil.dp2px(context, 10), 0);
        setMinWidth(DisplayUtil.dp2px(context, 45));
        setMaxWidth(DisplayUtil.dp2px(context, 200));
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        setText(attributeValue);
    }

}
