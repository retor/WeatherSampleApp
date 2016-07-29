package pro.retor.weathersampleapp.base.interctor;

import rx.Observable;
import rx.functions.Action0;

/**
 * Created by retor on 27.06.2016.
 */

public interface DefaultInteractor<M> {

    Observable<M> getModel();

    Action0 reset();
}
