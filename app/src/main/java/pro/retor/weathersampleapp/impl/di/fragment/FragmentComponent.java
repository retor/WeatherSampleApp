package pro.retor.weathersampleapp.impl.di.fragment;

import dagger.Component;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.weathersampleapp.impl.di.activity.ActivityComponent;
import pro.retor.weathersampleapp.impl.di.scopes.FragmentScope;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.ui.detail.DetailFragment;
import pro.retor.weathersampleapp.impl.ui.list.ListFragment;

/**
 * Created by retor on 10.04.2016.
 */
@FragmentScope
@Component(modules = {FragmentAdapters.class, PresentersFragmentModule.class},
        dependencies = ActivityComponent.class)
public interface FragmentComponent extends ActivityComponent{

    BasePresenter<ListFragment, ListModel> BASE_LIST_PRESENTER();

    BasePresenter<DetailFragment, DetailModel> BASE_DETAIL_PRESENTER();

    void inject(ListFragment fragment);
    void inject(DetailFragment fragment);

}
