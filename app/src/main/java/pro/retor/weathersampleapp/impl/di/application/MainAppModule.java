package pro.retor.weathersampleapp.impl.di.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.base.application.BaseApplication;


/**
 * Created by retor on 10.04.2016.
 */
@Module
public class MainAppModule {
    private BaseApplication application;

    public MainAppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    public Context providesContext(){
        return application;
    }

    @Provides
    public BaseApplication providesApplication(){
        return application;
    }
}
