package com.madreain.sku;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author madreain
 * @date 2019-07-29.
 * module：
 * description：
 */
@GlideModule
public class AppGlideModule extends com.bumptech.glide.module.AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .disallowHardwareConfig()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
                .format(DecodeFormat.PREFER_RGB_565);
        builder.setDefaultRequestOptions(requestOptions);
    }

}
