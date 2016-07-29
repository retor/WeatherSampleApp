package pro.retor.weathersampleapp.impl.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 27.06.2016.
 */

public class MainModel implements Parcelable {
    private boolean tabLandscape;

    public MainModel(boolean tabLandscape) {
        this.tabLandscape = tabLandscape;
    }

    public boolean isTabLandscape() {
        return tabLandscape;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "tabLandscape=" + tabLandscape +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.tabLandscape ? (byte) 1 : (byte) 0);
    }

    protected MainModel(Parcel in) {
        this.tabLandscape = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MainModel> CREATOR = new Parcelable.Creator<MainModel>() {
        @Override
        public MainModel createFromParcel(Parcel source) {
            return new MainModel(source);
        }

        @Override
        public MainModel[] newArray(int size) {
            return new MainModel[size];
        }
    };
}