package app.subbu.domain;

import app.subbu.mvp.model.CarTypes;
import app.subbu.repository.Repository;
import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class FetchCarTypesUsecase implements Usecase<CarTypes> {

    private Repository repository;
    private int page;
    private int pageSize;

    public FetchCarTypesUsecase(Repository repository) {
        this.repository = repository;
        pageSize = Repository.DEFAULT_PAGE_SIZE;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Observable<CarTypes> execute() {
        return repository.getCarTypes(page, pageSize);
    }
}
