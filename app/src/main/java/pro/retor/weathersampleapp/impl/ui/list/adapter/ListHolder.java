package pro.retor.weathersampleapp.impl.ui.list.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import butterknife.Bind;
import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.impl.models.generics.City;
import pro.retor.weathersampleapp.recyclerview.BaseRecyclerAdapter;

/**
 * Created by retor on 23.06.2016.
 */
public class ListHolder extends BaseRecyclerAdapter.ItemViewHolder<City> {
    @Bind(R.id.textView)
    AppCompatTextView cityName;

    public ListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void fillHolder(City item) {
        cityName.setText(item.getName());
    }
}
