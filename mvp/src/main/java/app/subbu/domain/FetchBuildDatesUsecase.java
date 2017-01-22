package app.subbu.domain;

import app.subbu.mvp.model.BuiltDates;
import app.subbu.repository.Repository;
import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class FetchBuildDatesUsecase implements Usecase<BuiltDates> {

    private Repository repository;
    private int page;
    private int pageSize;
    private int manufacturer;
    private String mainType;

    public FetchBuildDatesUsecase(Repository repository) {
        this.repository = repository;
        pageSize = Repository.DEFAULT_PAGE_SIZE;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    @Override
    public Observable<BuiltDates> execute() {
        return repository.getBuildDates(mainType, manufacturer, page, pageSize);
    }
}
