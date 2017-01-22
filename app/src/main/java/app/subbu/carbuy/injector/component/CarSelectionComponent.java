package app.subbu.carbuy.injector.component;

import app.subbu.carbuy.activity.CarTypesDialogActivity;
import app.subbu.carbuy.fragment.CarTypesFragment;
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

    void inject(CarTypesDialogActivity activity);
    void inject(CarTypesFragment fragment);
}
