package com.assignment.myandroidjournal;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
    public static App INSTANCE ;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;
    }

    public static App getINSTANCE() {
        return INSTANCE;
    }
}
