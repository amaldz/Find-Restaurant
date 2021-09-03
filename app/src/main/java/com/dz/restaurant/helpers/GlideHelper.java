package com.dz.restaurant.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dz.restaurant.R;

public class GlideHelper {

    public static void setImageUsingGlide(Context context, String url, final ImageView imageContainer){
        Glide.with(context)
                .asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        int width = imageContainer.getWidth();
                        int height = imageContainer.getHeight();
//                        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                        imageContainer.setImageDrawable(context.getResources().getDrawable(R.drawable.app_icon_fill));
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageContainer);
    }
}
