package app.subbu.mvp.presenter;

import app.subbu.mvp.view.IView;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public interface Presenter<T extends IView> {

    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(T view);

}
