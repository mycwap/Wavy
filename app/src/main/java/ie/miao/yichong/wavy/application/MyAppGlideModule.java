package ie.miao.yichong.wavy.application;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Author:   yichongmiao
 * CreateAt: 06/06/2018.
 *
 * Glide module
 * use to load picture
 */

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setLogLevel(Log.VERBOSE);
    }
}