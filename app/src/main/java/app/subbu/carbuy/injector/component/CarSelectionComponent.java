package app.subbu.carbuy.injector.component;

import app.subbu.carbuy.activity.BuiltDatesActivity;
import app.subbu.carbuy.activity.CarTypesActivity;
import app.subbu.carbuy.activity.MainTypesActivity;
import app.subbu.carbuy.fragment.BuiltDatesFragment;
import app.subbu.carbuy.fragment.CarTypesFragment;
import app.subbu.carbuy.fragment.MainTypesFragment;
import app.subbu.carbuy.injector.module.CarSelectionModule;
import app.subbu.carbuy.injector.scope.PerActivity;
import dagger.Component;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {CarSelectionModule.class})
public interface CarSelectionComponent {

    void inject(CarTypesActivity activity);
    void inject(CarTypesFragment fragment);

    void inject(MainTypesActivity activity);
    void inject(MainTypesFragment fragment);

    void inject(BuiltDatesActivity activity);
    void inject(BuiltDatesFragment fragment);
}
