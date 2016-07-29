package pro.retor.weathersampleapp.impl.di.data.network;

import com.example.errors.ErrorAdapter;
import com.example.errors.ErrorHandler;
import com.example.errors.ServerError;
import com.example.interceptors.HeaderInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pro.retor.weathersampleapp.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private String baseUrl;
    private String appId;

    public NetworkModule(String baseUrl, String appId) {
        this.baseUrl = baseUrl;
        this.appId = appId;
    }

    @Provides
    @Singleton
    public OkHttpClient providesHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new HeaderInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
        }
        client.connectTimeout(6 * 100, TimeUnit.MILLISECONDS);
        client.readTimeout(6 * 100, TimeUnit.MILLISECONDS);
        client.retryOnConnectionFailure(true);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                String units;
                switch (Locale.getDefault().getDisplayLanguage()) {
                    case "en":
                        units = "imperial";
                        break;
                    default:
                        units = "metric";
                }

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("appid", appId)
                        .addQueryParameter("lang", Locale.getDefault().getLanguage())
                        .addQueryParameter("units", units)
                        .addQueryParameter("type", "accurate")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return client.build();
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ServerError.class, new ErrorAdapter())
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    public ErrorHandler providesErrorHandler(Retrofit retrofit) {
        return new ErrorHandler(retrofit
                .responseBodyConverter(ServerError.class, new Annotation[0]));
    }
}
