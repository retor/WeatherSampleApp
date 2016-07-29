package pro.retor.weathersampleapp.base.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by retor on 03.03.2016.
 */
public class NetworkChecker {
    private ConnectivityManager manager;

    @Inject
    public NetworkChecker(ConnectivityManager connectivityManager) {
        this.manager = connectivityManager;
    }

    public boolean isNetworkConnected(){
        NetworkInfo ni = manager.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

}
