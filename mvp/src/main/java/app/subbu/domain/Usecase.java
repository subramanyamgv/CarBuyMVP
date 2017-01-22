package app.subbu.domain;

import io.reactivex.Observable;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public interface Usecase<T> {

    Observable<T> execute();
}
