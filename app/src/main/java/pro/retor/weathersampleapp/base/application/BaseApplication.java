package pro.retor.weathersampleapp.base.application;

import android.support.multidex.MultiDexApplication;

import pro.retor.weathersampleapp.impl.di.application.ApplicationComponent;


/**
 * Created by retor on 25.06.2016.
 */

public abstract class BaseApplication extends MultiDexApplication {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    protected abstract ApplicationComponent initComponent();

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null)
            applicationComponent=initComponent();
        return applicationComponent;
    }

    protected void setApplicationComponent(ApplicationComponent component) {
        applicationComponent = component;
    }

    public void clearAppComponent() {
        this.applicationComponent = null;
        System.runFinalization();
        System.gc();
    }
}
