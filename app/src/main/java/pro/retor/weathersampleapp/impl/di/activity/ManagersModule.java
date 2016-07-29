package pro.retor.weathersampleapp.impl.di.activity;

import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.base.application.BaseFragmentsManager;
import pro.retor.weathersampleapp.impl.di.scopes.ActivityScope;
import pro.retor.weathersampleapp.impl.managers.FragmentatorLandscape;
import pro.retor.weathersampleapp.impl.managers.FragmentatorNormal;

/**
 * Created by retor on 11.04.2016.
 */
@Module
public class ManagersModule {
    @ActivityScope
    @Provides
    public BaseFragmentsManager providesToolbarManager(Resources resources) {
        if (resources.getBoolean(R.bool.isTabletLand))
            return new FragmentatorLandscape(R.id.fragment);
        else
            return new FragmentatorNormal(R.id.fragment);
    }
}
