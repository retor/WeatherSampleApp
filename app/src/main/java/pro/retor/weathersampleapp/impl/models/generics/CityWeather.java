package pro.retor.weathersampleapp.impl.models.generics;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 27.06.2016.
 */

public class CityWeather implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public CityWeather() {
    }

    protected CityWeather(Parcel in) {
    }

    public static final Parcelable.Creator<CityWeather> CREATOR = new Parcelable.Creator<CityWeather>() {
        @Override
        public CityWeather createFromParcel(Parcel source) {
            return new CityWeather(source);
        }

        @Override
        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };
}
