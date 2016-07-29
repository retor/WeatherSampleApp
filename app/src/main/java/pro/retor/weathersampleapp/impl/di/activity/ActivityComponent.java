package pro.retor.weathersampleapp.impl.di.activity;


import dagger.Component;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.weathersampleapp.base.application.BaseFragmentsManager;
import pro.retor.weathersampleapp.base.interctor.DefaultInteractor;
import pro.retor.weathersampleapp.impl.di.application.ApplicationComponent;
import pro.retor.weathersampleapp.impl.di.scopes.ActivityScope;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.MainModel;
import pro.retor.weathersampleapp.impl.ui.main.MainActivity;


/**
 * Created by retor on 10.04.2016.
 */
@ActivityScope
@Component(modules = {ManagersModule.class, PresentersActivityModule.class},
        dependencies = ApplicationComponent.class)
public interface ActivityComponent extends ApplicationComponent{

    DefaultInteractor<MainModel> MAIN_MODEL_DEFAULT_INTERACTOR();

    DefaultInteractor<ListModel> LIST_MODEL_DEFAULT_INTERACTOR();

    DefaultInteractor<DetailModel> DETAIL_MODEL_DEFAULT_INTERACTOR();

    BaseFragmentsManager providesListFragmentManager();

    BasePresenter<MainActivity, MainModel> BASE_MAIN_PRESENTER();

    void inject(MainActivity activity);

}
