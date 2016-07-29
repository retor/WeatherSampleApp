package pro.retor.weathersampleapp.impl.ui.detail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import pro.retor.mymvp.RxBinder;
import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.base.application.DefaultFragment;
import pro.retor.weathersampleapp.base.utils.Utils;
import pro.retor.weathersampleapp.impl.di.fragment.DaggerFragmentComponent;
import pro.retor.weathersampleapp.impl.di.fragment.FragmentAdapters;
import pro.retor.weathersampleapp.impl.di.fragment.PresentersFragmentModule;
import pro.retor.weathersampleapp.impl.models.DetailModel;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by retor on 27.06.2016.
 */

public class DetailFragment extends DefaultFragment<DetailModel,DetailFragment,DetailPresenter> {

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    public static DetailFragment newInstance(Bundle arg) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    private ProgressDialog progressDialog;

    @Override
    protected int getLayout() {
        return R.layout.detail_fragment;
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

    public Func1<Observable<DetailModel>, Subscription> fillDetails(){
        return RxBinder.ui(new Action1<DetailModel>() {
            @Override
            public void call(DetailModel detailModel) {

            }
        }, showError());
    }
}
