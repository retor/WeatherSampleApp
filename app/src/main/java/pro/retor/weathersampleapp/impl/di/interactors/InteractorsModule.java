package pro.retor.weathersampleapp.impl.di.interactors;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.di.scopes.ActivityScope;
import pro.retor.weathersampleapp.impl.interactors.DetailInteractor;
import pro.retor.weathersampleapp.impl.interactors.ListInteractor;
import pro.retor.weathersampleapp.impl.interactors.MainInteractor;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.MainModel;

/**
 * Created by retor on 10.04.2016.
 */
@Module
public class InteractorsModule {
    @ActivityScope
    @Provides
    public DefaultInteractor<MainModel> providesBaseMainInteractor(MainInteractor interactor) {
        return interactor;
    }

    @ActivityScope
    @Provides
    public DefaultInteractor<ListModel> providesBaseListInteractor(ListInteractor interactor) {
        return interactor;
    }

    @ActivityScope
    @Provides
    public DefaultInteractor<DetailModel> providesBaseDetailInteractor(DetailInteractor interactor) {
        return interactor;
    }
}
