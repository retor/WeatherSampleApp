package pro.retor.weathersampleapp.impl.data.api;

import pro.retor.weathersampleapp.impl.data.api.responses.CityWeatherResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by retor on 29.06.2016.
 */

public interface WeatherMethods {
    @GET("find/?")
    Observable<Response<CityWeatherResponse>> getWeatherCity(@Query("lat")long lat, @Query("lon")long longitude);

    @GET("find/?")
    Observable<Response<CityWeatherResponse>> getWeatherCityByName(@Query("q")String cityName);
}
