package app.subbu.mvp.presenter;


import app.subbu.domain.FetchBuildDatesUsecase;
import app.subbu.mvp.model.BuiltDates;
import app.subbu.mvp.view.BuiltDatesView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class BuiltDatesPresenter implements Presenter<BuiltDatesView> {

    private FetchBuildDatesUsecase fetchBuildDatesUsecase;

    private BuiltDatesView builtDatesView;

    public BuiltDatesPresenter(FetchBuildDatesUsecase fetchBuildDatesUsecase) {
        this.fetchBuildDatesUsecase = fetchBuildDatesUsecase;
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
    public void attachView(BuiltDatesView view) {
        this.builtDatesView = view;
    }

    public void getCarTypes() {

        fetchBuildDatesUsecase.execute().subscribeOn(Schedulers.io())
                .subscribe(new Observer<BuiltDates>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuiltDates builtDates) {
                        builtDatesView.showBuiltDates(builtDates);
                    }

                    @Override
                    public void onError(Throwable e) {
                        builtDatesView.showError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
