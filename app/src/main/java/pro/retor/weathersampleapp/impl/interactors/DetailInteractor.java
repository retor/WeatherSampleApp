package pro.retor.weathersampleapp.impl.interactors;

import javax.inject.Inject;

import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.data.api.WeatherMethods;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import rx.Observable;
import rx.functions.Action0;

/**
 * Created by retor on 27.06.2016.
 */

public class DetailInteractor implements DefaultInteractor<DetailModel> {
    private WeatherMethods weatherApi;

    @Inject
    public DetailInteractor(WeatherMethods weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<DetailModel> getModel() {
        return Observable.just(new DetailModel());
    }

    @Override
    public Action0 reset() {
        return new Action0() {
            @Override
            public void call() {

            }
        };
    }
}
