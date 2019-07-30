package com.madreain.sku.utils;

import android.content.Context;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
public class DisplayUtil {

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
