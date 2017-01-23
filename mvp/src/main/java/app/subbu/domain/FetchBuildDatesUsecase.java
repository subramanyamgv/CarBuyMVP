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
    private String manufacturerId;
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

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    @Override
    public Observable<BuiltDates> execute() {
        return repository.getBuildDates(mainType, manufacturerId, page, pageSize);
    }
}
