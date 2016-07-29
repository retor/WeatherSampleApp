package pro.retor.weathersampleapp.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by retor on 27.04.2016.
 */
public abstract class BaseRecyclerAdapter<M, VH extends BaseRecyclerAdapter.ItemViewHolder<M>> extends RecyclerView.Adapter<VH> {
    private List<M> items;

    public BaseRecyclerAdapter() {
        this.items = new ArrayList<>();
    }

    public BaseRecyclerAdapter(List<M> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.fillHolder(getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<M> getItems() {
        return items;
    }

    public void setItems(List<M> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(M item){
        if (this.items == null)
            this.items = new ArrayList<>();
        items.add(item);
        notifyItemInserted(items.lastIndexOf(item));
    }

    public void addItems(List<M> itemsAdd){
        if (this.items == null)
            this.items = new ArrayList<>();
        int s = items.size();
        items.addAll(itemsAdd);
        notifyItemRangeInserted(s, items.size());
    }

    public M getItem(int position){
        return items.get(position);
    }

    public void insertItem(M item, int position){
        this.items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(M item){
        if (items.contains(item)) {
            int i = items.indexOf(item);
            items.remove(i);
            notifyItemRemoved(i);
        }
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public static abstract class ItemViewHolder<M> extends RecyclerView.ViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void fillHolder(M item);
    }
}
