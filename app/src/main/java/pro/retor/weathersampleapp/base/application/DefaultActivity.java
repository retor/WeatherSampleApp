package pro.retor.weathersampleapp.base.application;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import pro.retor.mymvp.application.activity.BaseActivity;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.mymvp.base.view.BaseView;
import pro.retor.weathersampleapp.impl.di.activity.ActivityComponent;
import pro.retor.weathersampleapp.impl.di.activity.DaggerActivityComponent;
import pro.retor.weathersampleapp.impl.di.activity.ManagersModule;
import pro.retor.weathersampleapp.impl.di.activity.PresentersActivityModule;
import pro.retor.weathersampleapp.impl.di.interactors.InteractorsModule;

/**
 * Created by retor on 25.06.2016.
 */

public abstract class DefaultActivity<M extends Parcelable, V extends BaseView, P extends BasePresenter<V, M>>
        extends BaseActivity<M, V, P> {
    private ActivityComponent component;

    @Inject
    protected P presenter;
    @Inject
    protected BaseFragmentsManager fragmentsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentsManager.setFragmentManager(getSupportFragmentManager());
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    public ActivityComponent getComponent(){
        if(component==null)
            component = DaggerActivityComponent.builder()
                    .applicationComponent(getBaseApplication().getApplicationComponent())
                    .presentersActivityModule(new PresentersActivityModule())
                    .interactorsModule(new InteractorsModule())
                    .managersModule(new ManagersModule())
                    .build();
        return component;
    }

    protected final BaseApplication getBaseApplication(){
        return (BaseApplication) getApplication();
    }
}
