package com.example.arithgo.app;

import android.app.Application;
import android.content.Context;

public class ArithGoApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ArithGoApp.context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
