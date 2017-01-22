package app.subbu.carbuy.injector.module;

import app.subbu.carbuy.injector.scope.PerActivity;
import app.subbu.domain.FetchBuildDatesUsecase;
import app.subbu.domain.FetchCarTypesUsecase;
import app.subbu.domain.FetchMainTypesUsecase;
import app.subbu.mvp.presenter.BuiltDatesPresenter;
import app.subbu.mvp.presenter.CarTypesPresenter;
import app.subbu.mvp.presenter.MainTypesPresenter;
import app.subbu.repository.Repository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */
@Module
public class CarSelectionModule {

    private Repository repository;

    @Provides
    @PerActivity
    public FetchCarTypesUsecase provideCarTypesUsecase(Repository repository) {
        return new FetchCarTypesUsecase(repository);
    }

    @Provides
    @PerActivity
    public FetchMainTypesUsecase provideMainTypesUsecase(Repository repository) {
        return new FetchMainTypesUsecase(repository);
    }

    @Provides
    @PerActivity
    public FetchBuildDatesUsecase provideBuildDatesUsecase(Repository repository) {
        return new FetchBuildDatesUsecase(repository);
    }

    @Provides
    @PerActivity
    public CarTypesPresenter provideCarTypesPresenter(FetchCarTypesUsecase fetchCarTypesUsecase) {
        return new CarTypesPresenter(fetchCarTypesUsecase);
    }

    @Provides
    @PerActivity
    public MainTypesPresenter provideMainTypesPresenter(FetchMainTypesUsecase fetchMainTypesUsecase) {
        return new MainTypesPresenter(fetchMainTypesUsecase);
    }

    @Provides
    @PerActivity
    public BuiltDatesPresenter provideBuiltDatesPresenter(FetchBuildDatesUsecase fetchBuildDatesUsecase) {
        return new BuiltDatesPresenter(fetchBuildDatesUsecase);
    }
}
