package pro.retor.weathersampleapp.impl.ui.list;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import pro.retor.mymvp.RxBinder;
import pro.retor.weathersampleapp.FragmentConstants;
import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.base.application.DefaultFragment;
import pro.retor.weathersampleapp.base.utils.Utils;
import pro.retor.weathersampleapp.impl.di.fragment.DaggerFragmentComponent;
import pro.retor.weathersampleapp.impl.di.fragment.FragmentAdapters;
import pro.retor.weathersampleapp.impl.di.fragment.PresentersFragmentModule;
import pro.retor.weathersampleapp.impl.models.ListModel;
import pro.retor.weathersampleapp.impl.models.generics.City;
import pro.retor.weathersampleapp.impl.ui.list.adapter.ListHolder;
import pro.retor.weathersampleapp.recyclerview.BaseRecyclerAdapter;
import pro.retor.weathersampleapp.recyclerview.RecyclerViewClickListener;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.MainThreadSubscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends DefaultFragment<ListModel, ListFragment, CityListPresenter> {
    @Bind(R.id.list)
    RecyclerView recyclerView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Inject
    BaseRecyclerAdapter<City, ListHolder> adapter;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;
    private AppCompatAutoCompleteTextView citySearch;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    public static ListFragment newInstance(Bundle arg) {
        ListFragment fragment = new ListFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    public Func1<Observable<String>, Subscription> initContent() {
        return RxBinder.ui(new Action1<String>() {
            @Override
            public void call(String s) {
                if (getBaseActivity().getSupportActionBar() != null)
                    getBaseActivity().getSupportActionBar().setTitle(s);
                initRecycler();
            }
        }, showError());
    }

/*    public Func1<Observable<Boolean>, Subscription> initFab(){
        return RxBinder.ui(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean)
                    fab.show();
                else
                    fab.hide();
            }
        }, showError());
    }

    public Observable<Boolean> fabState(){
        return RxFab.fabSetOnVisible(fab);
    }*/

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
/*        subscription.add(RxRecyclerView.scrollEvents(recyclerView).subscribe(recyclerViewScrollEvent -> {
            int visibleCount = recyclerView.getLayoutManager().getChildCount();
            if ((recyclerView.getLayoutManager().getItemCount() >= count) &&
                    (recyclerView.getLayoutManager().getItemCount() - visibleCount)
                            <=
                            ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() + visibleCount) {
                getPresenter().getMore();
            }
        }, this::showError));*/
    }

    public Observable<Integer> itemClick() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                RecyclerView.OnItemTouchListener listener = new RecyclerViewClickListener(recyclerView.getContext(),
                        new RecyclerViewClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                if (subscriber != null && !subscriber.isUnsubscribed())
                                    subscriber.onNext(position);
                            }
                        });
                recyclerView.addOnItemTouchListener(listener);
                subscriber.add(new MainThreadSubscription() {
                    @Override
                    protected void onUnsubscribe() {
                        recyclerView.removeOnItemTouchListener(listener);
                    }
                });
            }
        });
    }

    public Func1<Observable<String>, Subscription> showDetail() {
        return RxBinder.ui(new Action1<String>() {
            @Override
            public void call(String s) {
                Bundle arg = new Bundle();
                arg.putInt(FragmentConstants.DETAIL_ARG, 1);
                fragmentsManager.switchFragment(FragmentConstants.DETAIL_FRAGMENT, arg);
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        }, showError());
    }

    public Func1<Observable<List<City>>, Subscription> fillList() {
        return RxBinder.ui(new Action1<List<City>>() {
            @Override
            public void call(List<City> strings) {
                adapter.setItems(strings);
            }
        }, showError());
    }

    public Observable<Void> fabClick() {
        return RxView.clicks(fab);
    }

    @Override
    protected void inject() {
        DaggerFragmentComponent.builder()
                .activityComponent(getDefaultActivity().getComponent())
                .fragmentAdapters(new FragmentAdapters())
                .presentersFragmentModule(new PresentersFragmentModule())
                .build()
                .inject(this);
    }

    @Override
    public Action0 showProgress(boolean show) {
        return new Action0() {
            @Override
            public void call() {
                if (progressDialog == null)
                    progressDialog = Utils.progressShow(getActivity());
                if (show && !progressDialog.isShowing())
                    progressDialog.show();
                if (!show)
                    progressDialog.dismiss();

            }
        };
    }

    @Override
    public Action1<Throwable> showError() {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage(throwable.getLocalizedMessage())
                        .show();
            }
        };
    }

    public Func1<Observable<Void>, Subscription> showAddDialog() {
        return RxBinder.ui(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                initDialog();
                alertDialog.show();
            }
        }, showError());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (alertDialog != null)
            outState.putBundle("dialog", alertDialog.onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("dialog")) {
            initDialog();
            alertDialog.onRestoreInstanceState(savedInstanceState.getBundle("dialog"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    private void initDialog() {
        if (citySearch == null)
            citySearch = new AppCompatAutoCompleteTextView(getActivity());
        citySearch.setId(R.id.textView);
        citySearch.setSaveEnabled(true);
        citySearch.setSaveFromParentEnabled(true);
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("Add City")
                    .setView(citySearch)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO: 28.06.2016 Add to list
                            resetAutocomplete();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            resetAutocomplete();
                        }
                    })
                    .setOnCancelListener(dialog -> {
                        resetAutocomplete();
                    }).create();
    }

    private void resetAutocomplete() {
        citySearch.setText("");
    }
}
