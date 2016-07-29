package pro.retor.weathersampleapp.impl.ui.detail;

import javax.inject.Inject;

import pro.retor.mymvp.RxBinder;
import pro.retor.mymvp.base.presenter.Presenter;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.models.DetailModel;

/**
 * Created by retor on 27.06.2016.
 */

public class DetailPresenter extends Presenter<DetailFragment, DetailModel> {
    private DefaultInteractor<DetailModel> interactor;

    @Inject
    public DetailPresenter(DefaultInteractor<DetailModel> interactor) {
        this.interactor = interactor;
        setModel(new DetailModel());
    }

    @Override
    public void onViewAttached(DetailFragment view) {
        addSubscription(RxBinder.bind(interactor.getModel(), view.fillDetails()));
    }
}
