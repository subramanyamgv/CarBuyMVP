package app.subbu.mvp.presenter;


import app.subbu.domain.FetchCarTypesUsecase;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.view.CarTypesView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class CarTypesPresenter implements Presenter<CarTypesView> {

    private FetchCarTypesUsecase fetchCarTypesUsecase;

    private CarTypesView carTypesView;

    public CarTypesPresenter(FetchCarTypesUsecase fetchCarTypesUsecase) {
        this.fetchCarTypesUsecase = fetchCarTypesUsecase;
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
    public void attachView(CarTypesView view) {
        this.carTypesView = view;
    }

    public void getCarTypes() {

        fetchCarTypesUsecase.execute().subscribeOn(Schedulers.io())
                .subscribe(new Observer<CarTypes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CarTypes carTypes) {
                        carTypesView.showCarTypes(carTypes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        carTypesView.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
