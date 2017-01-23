package app.subbu.carbuy.repository;

import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.model.MainTypes;
import app.subbu.repository.Repository;
import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class MockRepository implements Repository {

    @Override
    public Observable<CarTypes> getCarTypes(int page, int pageSize) {
        return null;
    }

    @Override
    public Observable<MainTypes> getMainTypes(String manufacturer, int page, int pageSize) {
        return null;
    }

    @Override
    public Observable<BuiltDates> getBuildDates(String mainType, String manufacturer, int page, int pageSize) {
        return null;
    }
}
