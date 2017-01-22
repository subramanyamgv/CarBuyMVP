package app.subbu.carbuy.repository.network;

import app.subbu.carbuy.BuildConfig;
import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.model.MainTypes;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public interface RetrofitApiService {

    public static final String CAR_TYPES_URL = "car-types/manufacturer?wa_key=" + BuildConfig.apiKey;
    public static final String MAIN_TYPES_URL = "car-types/main-types?wa_key=" + BuildConfig.apiKey;
    public static final String BUILT_DATES_URL = "car-types/built-dates?wa_key=" + BuildConfig.apiKey;

    @GET(CAR_TYPES_URL)
    Observable<CarTypes> getCarTypes(@Query("page") int page,
                                     @Query("pageSize") int pageSize);

    @GET(MAIN_TYPES_URL)
    Observable<MainTypes> getMainTypes(@Query("manufacturer") int manufacturer,
                                       @Query("page") int page,
                                       @Query("pageSize") int pageSize);

    @GET(BUILT_DATES_URL)
    Observable<BuiltDates> getBuildDates(@Query(value = "main-type", encoded = true) String mainType,
                                         @Query("manufacturer") int manufacturer,
                                         @Query("page") int page,
                                         @Query("pageSize") int pageSize);

}
