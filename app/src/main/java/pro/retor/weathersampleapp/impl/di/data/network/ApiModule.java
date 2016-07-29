package pro.retor.weathersampleapp.impl.di.data.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.impl.data.api.WeatherMethods;
import retrofit2.Retrofit;


/**
 * Created by retor on 03.03.2016.
 */
@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides@Singleton
    public WeatherMethods providesWeatherApi(Retrofit retrofit){
        return retrofit.create(WeatherMethods.class);
    }

}

