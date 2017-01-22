package app.subbu.carbuy;

import android.app.Application;

import app.subbu.carbuy.injector.component.ApplicationComponent;
import app.subbu.carbuy.injector.component.DaggerApplicationComponent;
import app.subbu.carbuy.injector.module.ApplicationModule;
import app.subbu.carbuy.injector.module.NetworkModule;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class CarBuyMvpApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpInjector();
    }

    private void setUpInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
