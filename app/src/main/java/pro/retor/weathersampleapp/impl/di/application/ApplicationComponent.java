package pro.retor.weathersampleapp.impl.di.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import dagger.Component;
import pro.retor.weathersampleapp.base.utils.ImageLoader;
import pro.retor.weathersampleapp.base.utils.NetworkChecker;
import pro.retor.weathersampleapp.impl.di.data.DataComponent;
import pro.retor.weathersampleapp.impl.di.scopes.AppScope;

/**
 * Created by retor on 10.04.2016.
 */
@AppScope
@Component(modules = {AppHelpers.class},
        dependencies = DataComponent.class)
public interface ApplicationComponent extends DataComponent{
    Context CONTEXT();

    Resources RESOURCES();

    ImageLoader IMAGE_LOADER();

    ConnectivityManager CONNECTIVITY_MANAGER();

    SharedPreferences SHARED_PREFERENCES();

    NetworkChecker NETWORK_CHECKER();
}
