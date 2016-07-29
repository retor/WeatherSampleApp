package pro.retor.weathersampleapp.base;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by retor on 27.01.2016.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.d(getClass().getName(), String.format("Sending request %s on %s%n%s%n%s%n",
                request.url(), chain.connection(), request.method(), request.headers()));
        if (request.body()!=null)
        Log.d(getClass().getName(), String.format("Sending body %s",
                request.body().toString()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.d(getClass().getName(), String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        Log.d(getClass().getName(), String.format("Response response code %s%n Response message %s",
                response.code(), response.message()));
        return response;
    }
}
