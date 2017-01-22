package app.subbu.carbuy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.subbu.carbuy.R;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class CarSelectionActivity extends BaseActivity {

    public static final String TAG = "CarSelectionActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_selection);

        ButterKnife.bind(this);
    }

//    @Inject
//    CarTypesPresenter carSelectionPresenter;
//
//    private CarSelectionComponent carSelectionComponent;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_car_selection);
//
//        ButterKnife.bind(this);
//
//        carSelectionComponent = DaggerCarSelectionComponent.builder()
//                .applicationComponent(mAppComponent)
//                .carSelectionModule(new CarSelectionModule())
//                .build();
//
//        carSelectionComponent.inject(this);
//
//        initializePresenter();
//
//        carSelectionPresenter.getCarTypes();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        carSelectionPresenter.onStop();
//    }
//
//    private void initializePresenter() {
//        carSelectionPresenter.attachView(this);
//    }


}
