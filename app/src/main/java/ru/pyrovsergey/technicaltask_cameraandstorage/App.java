package ru.pyrovsergey.technicaltask_cameraandstorage;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App instance;
    private static AppComponent component;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
        instance = this;
        context = getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public static AppComponent getComponent() {
        return component;
    }

}
