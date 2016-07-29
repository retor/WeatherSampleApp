package com.example.interceptors;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by retor on 10.02.2016.
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Locale aDefault = Locale.getDefault();
        if (!aDefault.getLanguage().contains("en") &&
                !aDefault.getLanguage().contains("ru"))
            aDefault = Locale.ENGLISH;
        Request request = chain.request().newBuilder()
                .addHeader("X-REQUESTED-WITH", "XMLHttpRequest")
                .addHeader("LANGUAGE", aDefault.getLanguage())
                .build();
        return chain.proceed(request);
    }
}
