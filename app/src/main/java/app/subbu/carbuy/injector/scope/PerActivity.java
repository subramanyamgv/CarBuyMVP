package app.subbu.carbuy.injector.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@Scope
public @Retention(RUNTIME) @interface PerActivity {}
