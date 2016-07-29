package pro.retor.weathersampleapp.impl.ui.list;


import javax.inject.Inject;

import pro.retor.mymvp.RxBinder;
import pro.retor.mymvp.base.presenter.Presenter;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.generics.City;
import rx.Observable;


/**
 * Created by retor on 23.06.2016.
 */

public class CityListPresenter extends Presenter<ListFragment, ListModel> {
    private DefaultInteractor<ListModel> interactor;

    @Inject
    public CityListPresenter(DefaultInteractor<ListModel> interactor) {
        this.interactor = interactor;
        setModel(new ListModel());
    }

    @Override
    public void onViewAttached(ListFragment view) {
/*        addSubscription(RxBinder.bind(Observable.just(getModel().isFabShown()), view.initFab()));
        addSubscription(RxBinder.bind(view.fabState(), booleanObservable -> booleanObservable.subscribe(aBoolean -> getModel().setFabShown(aBoolean))));*/
        addSubscription(RxBinder.bind(view.fabClick(),view.showAddDialog()));
        addSubscription(RxBinder.bind(view.needUpdate().map(aBoolean -> "Weather city list"), view.initContent()));
        addSubscription(RxBinder.bind(view.needUpdate()
                .switchMap(aBoolean1 -> {
                    if (aBoolean1)
                        return interactor.getModel()
                            .doOnNext(this::setModel)
                            .map(ListModel::getItems);
                    else
                        return Observable.just(getModel().getItems());
                }), view.fillList()));

/*        addSubscription(RxBinder.bind(Observable.just(getModel())
                .filter(listModel -> listModel != null && listModel.getLastClicked() > 0)
                .map(listModel1 -> listModel1.getItems().get(listModel1.getLastClicked()).getName())
                .doOnCompleted(() -> getModel().setLastClicked(0)),
                view.showDetail()));*/

        addSubscription(RxBinder.bind(view.itemClick()
                .doOnNext(integer1 -> getModel().setLastClicked(integer1))
                .flatMap(integer -> Observable
                        .from(getModel().getItems())
                        .filter(city -> integer == city.getId())
                        .map(City::getName)), view.showDetail()));
    }
}
