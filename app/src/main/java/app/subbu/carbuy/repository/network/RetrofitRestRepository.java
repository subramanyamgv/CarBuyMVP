package app.subbu.carbuy.repository.network;

import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.model.MainTypes;
import app.subbu.repository.Repository;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class RetrofitRestRepository implements Repository {

    private RetrofitApiService apiService;

    public RetrofitRestRepository(Retrofit retrofit) {
        apiService = retrofit.create(RetrofitApiService.class);
    }

    @Override
    public Observable<CarTypes> getCarTypes(int page, int pageSize) {
        return apiService.getCarTypes(page, pageSize);
    }

    @Override
    public Observable<MainTypes> getMainTypes(String manufacturer, int page, int pageSize) {
        return apiService.getMainTypes(manufacturer, page, pageSize);
    }

    @Override
    public Observable<BuiltDates> getBuildDates(String mainType, String manufacturer, int page, int pageSize) {
        return apiService.getBuildDates(mainType, manufacturer, page, pageSize);
    }
}
