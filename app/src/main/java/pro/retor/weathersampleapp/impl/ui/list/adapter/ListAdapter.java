package pro.retor.weathersampleapp.impl.ui.list.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.impl.models.generics.City;
import pro.retor.weathersampleapp.recyclerview.BaseRecyclerAdapter;

/**
 * Created by retor on 23.06.2016.
 */
public class ListAdapter extends BaseRecyclerAdapter<City, ListHolder> {
    @Inject
    public ListAdapter() {
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false));
    }
}
