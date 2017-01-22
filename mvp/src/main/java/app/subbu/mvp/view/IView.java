package app.subbu.mvp.view;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public interface IView {

    void showLoading();

    void showEmpty();

    void showError(Throwable e);
}
