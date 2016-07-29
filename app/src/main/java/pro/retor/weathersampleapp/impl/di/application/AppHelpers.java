package pro.retor.weathersampleapp.impl.di.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.base.utils.ImageLoader;
import pro.retor.weathersampleapp.base.utils.NetworkChecker;
import pro.retor.weathersampleapp.impl.di.scopes.AppScope;


/**
 * Created by retor on 10.04.2016.
 */
@Module
public class AppHelpers {

    @Provides
    @AppScope
    public ConnectivityManager providesConnectivityManager(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
    }

    @Provides
    @AppScope
    public SharedPreferences providesPreferencesManager(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @AppScope
    public NetworkChecker providesNetworkChecker(ConnectivityManager manager) {
        return new NetworkChecker(manager);
    }

    @AppScope
    @Provides
    public ImageLoader providesImageLoader(Context context) {
        return new ImageLoader(context);
    }

    @AppScope
    @Provides
    public Resources providesResources(Context context) {
        return context.getResources();
    }
}
