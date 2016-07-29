package pro.retor.weathersampleapp.impl.models;

import android.os.Parcel;
import android.os.Parcelable;

import pro.retor.weathersampleapp.impl.models.generics.City;
import pro.retor.weathersampleapp.impl.models.generics.CityWeather;

/**
 * Created by retor on 27.06.2016.
 */

public class DetailModel implements Parcelable {
    private City city;
    private CityWeather weather;

    public DetailModel() {
    }

    public DetailModel(City city, CityWeather weather) {
        this.city = city;
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CityWeather getWeather() {
        return weather;
    }

    public void setWeather(CityWeather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "DetailModel{" +
                "city=" + city +
                ", weather=" + weather +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeParcelable(this.weather, flags);
    }

    protected DetailModel(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.weather = in.readParcelable(CityWeather.class.getClassLoader());
    }

    public static final Parcelable.Creator<DetailModel> CREATOR = new Parcelable.Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel source) {
            return new DetailModel(source);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };
}
