package pro.retor.weathersampleapp;

import pro.retor.weathersampleapp.base.application.BaseApplication;
import pro.retor.weathersampleapp.impl.di.application.AppHelpers;
import pro.retor.weathersampleapp.impl.di.application.ApplicationComponent;
import pro.retor.weathersampleapp.impl.di.application.DaggerApplicationComponent;
import pro.retor.weathersampleapp.impl.di.application.MainAppModule;
import pro.retor.weathersampleapp.impl.di.data.DaggerDataComponent;
import pro.retor.weathersampleapp.impl.di.data.DataComponent;
import pro.retor.weathersampleapp.impl.di.data.network.ApiModule;
import pro.retor.weathersampleapp.impl.di.data.network.NetworkModule;

/**
 * Created by retor on 27.06.2016.
 */

public class MainApplication extends BaseApplication {
    private DataComponent dataComponent;
    @Override
    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .dataComponent(getDataComponent())
                .appHelpers(new AppHelpers()).build();
    }

    protected DataComponent getDataComponent(){
        if (dataComponent==null)
            dataComponent = DaggerDataComponent.builder()
                    .mainAppModule(new MainAppModule(this))
                    .networkModule(new NetworkModule("http://api.openweathermap.org/data/2.5/", "9159b456ec26b279fd43db113102ae18"))
                    .apiModule(new ApiModule())
//                    .dBModule(new DBModule())
                    .build();
            return dataComponent;
    }
}
