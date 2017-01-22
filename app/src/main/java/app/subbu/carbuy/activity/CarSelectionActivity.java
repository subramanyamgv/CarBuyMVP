package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import app.subbu.carbuy.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class CarSelectionActivity extends BaseActivity {

    public static final String TAG = "CarSelectionActivity";

    @BindView(R.id.edt_car_type)
    EditText mEditCarType;

    @BindView(R.id.edt_main_type)
    EditText mEditMainType;

    @BindView(R.id.edt_builtdate)
    EditText mEditBuiltDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_selection);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.edt_car_type)
    public void OnCarTypeClicked() {
        startActivityForResult(new Intent(getApplicationContext(), CarTypesDialogActivity.class), 0);
    }

    @OnClick(R.id.edt_main_type)
    public void OnMainTypeClicked() {

    }

    @OnClick(R.id.edt_builtdate)
    public void OnBuiltDateClicked() {

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
