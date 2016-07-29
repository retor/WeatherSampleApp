package pro.retor.weathersampleapp.base.utils;

import android.support.design.widget.FloatingActionButton;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * Created by retor on 28.06.2016.
 */

public class RxFab {
    public static final Observable<Boolean> fabSetOnVisible(FloatingActionButton fab){
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (subscriber!=null && !subscriber.isUnsubscribed()){
                    fab.show(new FloatingActionButton.OnVisibilityChangedListener() {
                        @Override
                        public void onShown(FloatingActionButton fab) {
                            super.onShown(fab);
                            subscriber.onNext(true);
                        }

                        @Override
                        public void onHidden(FloatingActionButton fab) {
                            super.onHidden(fab);
                            subscriber.onNext(false);
                        }
                    });
                }
                subscriber.add(new MainThreadSubscription() {
                    @Override
                    protected void onUnsubscribe() {
                        fab.show(null);
                    }
                });
            }
        });
    }
}
