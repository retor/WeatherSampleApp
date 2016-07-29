package pro.retor.weathersampleapp.base.interctor;

import java.util.List;

import rx.Observable;

/**
 * Created by retor on 27.06.2016.
 */

public interface DefailtListInteractor<M> extends DefaultInteractor<M> {
    Observable<List<M>> getModelsList();
}
