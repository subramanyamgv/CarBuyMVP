package app.subbu.domain;

import app.subbu.mvp.model.MainTypes;
import app.subbu.repository.Repository;
import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class FetchMainTypesUsecase implements Usecase<MainTypes> {

    private Repository repository;
    private int page;
    private int pageSize;
    private String manufacturerId;

    public FetchMainTypesUsecase(Repository repository) {
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

    @Override
    public Observable<MainTypes> execute() {
        return repository.getMainTypes(manufacturerId, page, pageSize);
    }
}
