package pro.retor.weathersampleapp.impl.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import pro.retor.mymvp.RxBinder;
import pro.retor.weathersampleapp.FragmentConstants;
import pro.retor.weathersampleapp.R;
import pro.retor.weathersampleapp.base.application.DefaultActivity;
import pro.retor.weathersampleapp.base.utils.Utils;
import pro.retor.weathersampleapp.impl.models.MainModel;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends DefaultActivity<MainModel, MainActivity, MainPresenter> {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ProgressDialog progressDialog;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected View createContent(Bundle savedInstanceState) {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    public Action0 showProgress(boolean show) {
        return new Action0() {
            @Override
            public void call() {
                if (progressDialog == null)
                    progressDialog = Utils.progressShow(MainActivity.this);
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
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error")
                        .setMessage(throwable.getLocalizedMessage())
                        .show();
            }
        };
    }

    public Func1<Observable<Boolean>, Subscription> initContent() {
        return RxBinder.ui(new Action1<Boolean>() {
            @Override
            public void call(Boolean landscape) {
                setSupportActionBar(toolbar);
                if (landscape) {
                    Bundle arg = new Bundle();
                    arg.putInt(FragmentConstants.DETAIL_ARG, 1);
                    fragmentsManager.switchFragment(FragmentConstants.DETAIL_FRAGMENT, arg);
                }else{
                    fragmentsManager.switchFragment(FragmentConstants.LIST_FRAGMENT);
                }
            }
        }, showError());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBaseApplication().clearAppComponent();
    }
}
