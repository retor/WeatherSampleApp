package pro.retor.weathersampleapp.base.application;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import pro.retor.mymvp.application.fragment.BaseFragment;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.mymvp.base.view.BaseView;

/**
 * Created by retor on 25.06.2016.
 */

public abstract class DefaultFragment<M extends Parcelable, V extends BaseView, P extends BasePresenter<V, M>>
        extends BaseFragment<M, V, P> {
    @Inject
    protected P presenter;
    @Inject
    protected BaseFragmentsManager fragmentsManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @LayoutRes
    protected abstract int getLayout();

    @Override
    public P getPresenter() {
        return presenter;
    }

    protected final DefaultActivity getDefaultActivity(){
        return (DefaultActivity) getActivity();
    }
}
