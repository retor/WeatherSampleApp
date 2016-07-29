package pro.retor.weathersampleapp.impl.di.fragment;

import dagger.Module;
import dagger.Provides;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.weathersampleapp.impl.di.scopes.FragmentScope;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.ui.detail.DetailFragment;
import pro.retor.weathersampleapp.impl.ui.detail.DetailPresenter;
import pro.retor.weathersampleapp.impl.ui.list.CityListPresenter;
import pro.retor.weathersampleapp.impl.ui.list.ListFragment;


/**
 * Created by retor on 10.04.2016.
 */
@Module
public class PresentersFragmentModule {
    @FragmentScope
    @Provides
    public BasePresenter<ListFragment, ListModel> providesListFragmentPresenter(CityListPresenter presenter){
        return presenter;
    }

    @FragmentScope
    @Provides
    public BasePresenter<DetailFragment, DetailModel> providesDetailFragmentPresenter(DetailPresenter presenter){
        return presenter;
    }
}
