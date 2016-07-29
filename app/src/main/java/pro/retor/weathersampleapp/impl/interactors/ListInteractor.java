package pro.retor.weathersampleapp.impl.interactors;

import java.util.List;

import javax.inject.Inject;

import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.generics.City;
import rx.Observable;
import rx.functions.Action0;


/**
 * Created by retor on 23.06.2016.
 */

public class ListInteractor implements DefaultInteractor<ListModel> {

    private List<City> stringsList;

    @Inject
    public ListInteractor() {
    }

    @Override
    public Observable<ListModel> getModel() {
        return Observable.range(0, 25)
                .map(integer -> new City(integer, "City is " + String.valueOf(integer))).toList()
                .doOnNext(strings -> stringsList = strings)
                .map(ListModel::new);
    }

    @Override
    public Action0 reset() {
        return new Action0() {
            @Override
            public void call() {
                stringsList.clear();
            }
        };
    }
}
