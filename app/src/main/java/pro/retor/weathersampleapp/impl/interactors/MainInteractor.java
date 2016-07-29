package pro.retor.weathersampleapp.impl.interactors;

import android.content.res.Resources;

import javax.inject.Inject;

import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.models.MainModel;
import rx.Observable;
import rx.functions.Action0;

/**
 * Created by retor on 27.06.2016.
 */

public class MainInteractor implements DefaultInteractor<MainModel> {
    private Resources resources;

    @Inject
    public MainInteractor(Resources resources) {
        this.resources = resources;
    }

    @Override
    public Observable<MainModel> getModel() {
        return Observable.just(new MainModel(resources.getBoolean(R.bool.isTabletLand)));
    }

    @Override
    public Action0 reset() {
        return new Action0() {
            @Override
            public void call() {
                resources = null;
            }
        };
    }
}
