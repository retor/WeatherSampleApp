package pro.retor.weathersampleapp.impl.di.activity;

import dagger.Module;
import dagger.Provides;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.weathersampleapp.impl.di.interactors.InteractorsModule;
import pro.retor.weathersampleapp.impl.di.scopes.ActivityScope;
import pro.retor.weathersampleapp.impl.models.MainModel;
import pro.retor.weathersampleapp.impl.ui.main.MainActivity;
import pro.retor.weathersampleapp.impl.ui.main.MainPresenter;


/**
 * Created by retor on 10.04.2016.
 */
@Module(includes = InteractorsModule.class)
public class PresentersActivityModule {
    @ActivityScope
    @Provides
    public BasePresenter<MainActivity, MainModel> providesBaseMainPresenter(MainPresenter presenter){
        return presenter;
    }

/*    @ActivityScope
    @Provides
    public BaseCityListPresenter<ListFragment, ListCity> providesBaseCityListPresenter(CityListPresenter presenter){
        return presenter;
    }*/
}
