package app.subbu.carbuy.injector.module;

import android.app.Application;

import app.subbu.carbuy.CarBuyMvpApplication;
import app.subbu.carbuy.injector.scope.PerApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@Module
public class ApplicationModule {

    private final CarBuyMvpApplication application;

    public ApplicationModule(CarBuyMvpApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    Application provideApplication(){
        return application;
    }

    @Provides
    @PerApplication
    CarBuyMvpApplication provideMvpApplication() {
        return application;
    }
}
