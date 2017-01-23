package app.subbu.mvp.presenter;


import app.subbu.domain.FetchMainTypesUsecase;
import app.subbu.mvp.model.MainTypes;
import app.subbu.mvp.view.MainTypesView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class MainTypesPresenter implements Presenter<MainTypesView> {

    private FetchMainTypesUsecase fetchMainTypesUsecase;

    private MainTypesView mainTypesView;

    public MainTypesPresenter(FetchMainTypesUsecase fetchMainTypesUsecase) {
        this.fetchMainTypesUsecase = fetchMainTypesUsecase;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(MainTypesView view) {
        this.mainTypesView = view;
    }

    public void getMainTypes(String manufacturerId, int page, int pageSize) {

        mainTypesView.showLoading();

        fetchMainTypesUsecase.setPage(page);
        fetchMainTypesUsecase.setPageSize(pageSize);
        fetchMainTypesUsecase.setManufacturerId(manufacturerId);

        fetchMainTypesUsecase.execute().subscribeOn(Schedulers.io())
                .subscribe(new Observer<MainTypes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainTypes mainTypes) {
                        mainTypesView.showMainTypes(mainTypes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainTypesView.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
