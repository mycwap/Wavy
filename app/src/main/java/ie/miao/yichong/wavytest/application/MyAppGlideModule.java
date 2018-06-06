package ie.miao.yichong.wavytest.application;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by yichongmiao on 17/12/2017.
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