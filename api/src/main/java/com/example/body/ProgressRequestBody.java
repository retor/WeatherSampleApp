package com.example.body;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by retor on 17.02.2016.
 */
public class ProgressRequestBody extends RequestBody {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private File mFile;

    private Observer<Integer> inner = new Observer<Integer>() {
        @Override
        public void onCompleted() {
            uploadSubscriber.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            uploadSubscriber.onError(e);
        }

        @Override
        public void onNext(Integer integer) {
            uploadSubscriber.onNext(integer);
        }
    };
    private Observer<Integer> uploadSubscriber;
    private Scheduler mainThread;

    public ProgressRequestBody(final File file, final Observer<Integer> listener, Scheduler mainThread) {
        mFile = file;
        uploadSubscriber = listener;
        this.mainThread = mainThread;
    }

    @Override
    public MediaType contentType() {
        // i want to upload only images
        return MediaType.parse("image/*");
    }

    @Override
    public void writeTo(final BufferedSink sink) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber != null && !subscriber.isUnsubscribed()) {
                    try {
                        long fileLength = mFile.length();
                        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                        FileInputStream in = new FileInputStream(mFile);
                        long uploaded = 0;
                        int read;

                        while ((read = in.read(buffer)) != -1) {
                            int progress = (int) ((100 * uploaded) / fileLength);
                            if (!subscriber.isUnsubscribed())
                                subscriber.onNext(progress);
                            uploaded += read;
                            sink.write(buffer, 0, read);
                        }
                    } catch (IOException e) {
                        if (!subscriber.isUnsubscribed())
                            subscriber.onError(e);
                    }
                    subscriber.onCompleted();
                }
            }
        })
                .filter(new Func1<Integer, Boolean>() {
                    int prev;

                    @Override
                    public Boolean call(Integer integer) {
                        if (prev != integer && integer > prev) {
                            prev = integer;
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .onBackpressureBuffer()
//                .subscribeOn(Schedulers.io())
                .observeOn(mainThread)
                .subscribe(inner);
    }

    public void setUploadSubscriber(Subscriber<Integer> uploadSubscriber) {
        this.uploadSubscriber = uploadSubscriber;
    }

    public void resetCallback() {
//        this.mListener = null;
        this.uploadSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

            }
        };
    }

    public interface UploadCallbacks {
        void onProgressUpdate(int percentage);

        void onError();

        void onFinish();
    }
}
