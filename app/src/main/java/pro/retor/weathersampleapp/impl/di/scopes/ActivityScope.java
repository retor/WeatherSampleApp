package pro.retor.weathersampleapp.impl.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Created by retor on 03.03.2016.
 */
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
