package pro.retor.weathersampleapp.base.interctor;

import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.generics.City;
import rx.Observable;

/**
 * Created by retor on 27.06.2016.
 */
public interface BaseCityListInteractor<M extends City> extends DefaultInteractor<ListModel> {
    Observable<City> getById(int id);
}
