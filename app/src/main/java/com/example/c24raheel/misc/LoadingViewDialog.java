package com.example.c24raheel.misc;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.c24raheel.R;

public class LoadingViewDialog {
    Activity activity;
    Dialog dialog;

    public LoadingViewDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_loading);
        ImageView gifImageView = dialog.findViewById(R.id.custom_loading_imageView);
        /*GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        Glide.with(activity)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);*/

        Glide.with(activity)
                .asGif()
                .load(R.drawable.loading)
                .centerCrop()
                .into(gifImageView);
        dialog.show();
    }

    public void hideDialog() {
        if(dialog != null && dialog.isShowing())
        dialog.dismiss();
    }

}

