package pro.retor.mymvp.application.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import pro.retor.mymvp.application.activity.BaseActivity;
import pro.retor.mymvp.base.presenter.BasePresenter;
import pro.retor.mymvp.base.view.BaseView;
import pro.retor.mymvp.application.LiveDelegate;
import rx.Observable;

/**
 * Created by retor on 12.04.2016.
 */
public abstract class BaseFragment<M extends Parcelable, V extends BaseView, P extends BasePresenter<V,M>>
        extends Fragment implements BaseView{
    private LiveDelegate<M, V, P> delegate;
    private boolean needUpdate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        delegate = new LiveDelegate<>();
        if (savedInstanceState != null)
            needUpdate = false;
        else
            needUpdate = true;
        delegate.setPresenter(getPresenter());
        delegate.onCreate((V) this, savedInstanceState, getArguments());
    }

    protected abstract P getPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onStart() {
        super.onStart();
        delegate.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSavedState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        delegate.onStop();
    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
        delegate.onDestroy();
    }

    @Override
    public Observable<Boolean> needUpdate() {
        return Observable.just(needUpdate);
    }

    protected abstract void inject();

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
