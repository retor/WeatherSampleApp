package pro.retor.weathersampleapp.impl.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import pro.retor.weathersampleapp.impl.models.generics.City;

/**
 * Created by retor on 27.06.2016.
 */
public class ListModel implements Parcelable {
    private int lastPosition;
    private int lastClicked;
    private List<City> items;
    private boolean fabShown = true;

    public ListModel() {
    }

    public ListModel(List<City> items) {
        this.items = items;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public int getLastClicked() {
        return lastClicked;
    }

    public void setLastClicked(int lastClicked) {
        this.lastClicked = lastClicked;
    }

    public List<City> getItems() {
        return items;
    }

    public void setItems(List<City> items) {
        this.items = items;
    }

    public boolean isFabShown() {
        return fabShown;
    }

    public void setFabShown(boolean fabShown) {
        this.fabShown = fabShown;
    }

    @Override
    public String toString() {
        return "ListModel{" +
                "lastPosition=" + lastPosition +
                ", lastClicked=" + lastClicked +
                ", items=" + items +
                ", fabShown=" + fabShown +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.lastPosition);
        dest.writeInt(this.lastClicked);
        dest.writeTypedList(this.items);
        dest.writeByte(this.fabShown ? (byte) 1 : (byte) 0);
    }

    protected ListModel(Parcel in) {
        this.lastPosition = in.readInt();
        this.lastClicked = in.readInt();
        this.items = in.createTypedArrayList(City.CREATOR);
        this.fabShown = in.readByte() != 0;
    }

    public static final Creator<ListModel> CREATOR = new Creator<ListModel>() {
        @Override
        public ListModel createFromParcel(Parcel source) {
            return new ListModel(source);
        }

        @Override
        public ListModel[] newArray(int size) {
            return new ListModel[size];
        }
    };
}
