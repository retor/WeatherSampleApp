package pro.retor.weathersampleapp.impl.ui.main;

import javax.inject.Inject;

import pro.retor.mymvp.RxBinder;
import pro.retor.mymvp.base.presenter.Presenter;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.models.MainModel;

/**
 * Created by retor on 23.06.2016.
 */

public class MainPresenter extends Presenter<MainActivity, MainModel> {
    private DefaultInteractor<MainModel> interactor;

    @Inject
    public MainPresenter(DefaultInteractor<MainModel> interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewAttached(MainActivity view) {
        addSubscription(RxBinder.bind(interactor.getModel().map(MainModel::isTabLandscape),view.initContent()));
    }
}
