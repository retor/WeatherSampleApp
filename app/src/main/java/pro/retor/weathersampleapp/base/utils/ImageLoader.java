package pro.retor.weathersampleapp.base.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import pro.retor.weathersampleapp.BuildConfig;
import pro.retor.weathersampleapp.R;


public class ImageLoader {

    private static volatile ImageLoader instance = null;
    private Picasso picasso;
    @DrawableRes
    private int errorRes = R.mipmap.ic_launcher;

    @Inject
    public ImageLoader(Context context) {
            this.picasso = new Picasso.Builder(context)
                    .downloader(new OkHttp3Downloader(context, ((32 * 1024) * 1024)))
                    .build();
            if (BuildConfig.DEBUG) {
                this.picasso.setIndicatorsEnabled(true);
                this.picasso.setLoggingEnabled(true);
            }
           Picasso.setSingletonInstance(this.picasso);
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public static ImageLoader getInstance(Context context) {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                instance = new ImageLoader(context);
            }
        }
        return instance;
    }

    public void loadPic(String url, ImageView into) {
        picasso.load(url).error(errorRes).fit().into(into);
    }

    public void loadCrop(final String url, final ImageView into) {
        picasso.load(url).error(errorRes).fit().centerCrop().into(into);
    }

    public void loadInShow(final String url, final ImageView into) {
        picasso.load(url).error(errorRes).fit().centerInside().into(into);
    }

    public void loadPic(int resource, ImageView into) {
        picasso.load(resource).error(errorRes).fit().into(into);
    }
}
