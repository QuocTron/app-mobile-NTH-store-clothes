package com.group7.appsellclothes.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.group7.appsellclothes.R;

import java.io.Serializable;

public class CustomProgressDialog extends Dialog implements Serializable {

    public CustomProgressDialog(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null);
        setContentView(view);
    }
}

