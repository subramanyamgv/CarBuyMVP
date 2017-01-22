package app.subbu.carbuy.injector.component;

import android.app.Application;

import app.subbu.carbuy.CarBuyMvpApplication;
import app.subbu.carbuy.injector.module.ApplicationModule;
import app.subbu.carbuy.injector.module.NetworkModule;
import app.subbu.carbuy.injector.scope.PerApplication;
import app.subbu.repository.Repository;
import dagger.Component;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Application application();

    CarBuyMvpApplication carBuyMvpApplication();

    Repository repository();

}
