package pro.retor.weathersampleapp.impl.di.fragment;

import dagger.Module;
import dagger.Provides;
import pro.retor.weathersampleapp.impl.di.scopes.FragmentScope;
import pro.retor.weathersampleapp.impl.models.generics.City;
import pro.retor.weathersampleapp.impl.ui.list.adapter.ListAdapter;
import pro.retor.weathersampleapp.impl.ui.list.adapter.ListHolder;
import pro.retor.weathersampleapp.recyclerview.BaseRecyclerAdapter;


@Module
public class FragmentAdapters {
    @FragmentScope
    @Provides
    public BaseRecyclerAdapter<City, ListHolder> providesMyRoutesAdapter(){
        return new ListAdapter();
    }
}
