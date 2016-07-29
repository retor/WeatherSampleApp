package pro.retor.weathersampleapp.impl.di.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pro.retor.weathersampleapp.impl.data.api.WeatherMethods;
import pro.retor.weathersampleapp.impl.di.data.db.DBModule;
import pro.retor.weathersampleapp.impl.di.data.network.ApiModule;

/**
 * Created by retor on 29.06.2016.
 */
@Singleton
@Component(modules = {DBModule.class, ApiModule.class})
public interface DataComponent {
    Context CONTEXT();
    WeatherMethods WEATHER_API();
}
