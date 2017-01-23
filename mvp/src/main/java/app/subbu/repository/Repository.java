package app.subbu.repository;

import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.model.MainTypes;
import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public interface Repository {

    public static final int DEFAULT_PAGE_SIZE = 10;

    public Observable<CarTypes> getCarTypes(int page, int pageSize);

    public Observable<MainTypes> getMainTypes(String manufacturer, int page, int pageSize);

    public Observable<BuiltDates> getBuildDates(String mainType, String manufacturer, int page, int pageSize);
}
