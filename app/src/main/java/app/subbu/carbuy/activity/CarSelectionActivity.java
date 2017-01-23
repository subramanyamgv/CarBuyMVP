package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.BuiltDate;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class CarSelectionActivity extends BaseActivity {

    public static final String TAG = "CarSelectionActivity";

    public static final int REQUEST_CODE_CAR_TYPE = 0;
    public static final int REQUEST_CODE_MAIN_TYPE = 1;
    public static final int REQUEST_CODE_BUILT_YEAR = 3;

    @BindView(R.id.edt_car_type)
    EditText mEditCarType;

    @BindView(R.id.edt_main_type)
    EditText mEditMainType;

    @BindView(R.id.edt_builtdate)
    EditText mEditBuiltDate;

    private Manufacturer manufacturer;
    private Model model;
    private BuiltDate builtDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.edt_car_type)
    public void OnCarTypeClicked() {
        startActivityForResult(new Intent(getApplicationContext(), CarTypesDialogActivity.class), REQUEST_CODE_CAR_TYPE);
    }

    @OnClick(R.id.edt_main_type)
    public void OnMainTypeClicked() {

        if (manufacturer == null)
            return;

        //Send Manufacturer details
        Intent intent = new Intent(getApplicationContext(), MainTypesDialogActivity.class);
        intent.putExtra(CarTypesDialogActivity.INTENT_EXTRA, manufacturer);

        startActivityForResult(intent, REQUEST_CODE_MAIN_TYPE);
    }

    @OnClick(R.id.edt_builtdate)
    public void OnBuiltDateClicked() {

        if (model == null || manufacturer == null)
            return;

        //Send Manufacturer and Model
        Intent intent = new Intent(getApplicationContext(), BuiltDatesDialogActivity.class);
        intent.putExtra(CarTypesDialogActivity.INTENT_EXTRA, manufacturer);
        intent.putExtra(MainTypesDialogActivity.INTENT_EXTRA, model);

        startActivityForResult(intent, REQUEST_CODE_BUILT_YEAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_CODE_CAR_TYPE) {
            Manufacturer manufacturer = data.getParcelableExtra(CarTypesDialogActivity.INTENT_EXTRA);
            setManufacturer(manufacturer);
        } else if (requestCode == REQUEST_CODE_MAIN_TYPE) {
            Model model = data.getParcelableExtra(MainTypesDialogActivity.INTENT_EXTRA);
            setModel(model);
        } else if (requestCode == REQUEST_CODE_BUILT_YEAR) {
            BuiltDate builtDate = data.getParcelableExtra(BuiltDatesDialogActivity.INTENT_EXTRA);
            setBuiltDate(builtDate);
        }
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        mEditCarType.setText(manufacturer.getManufacturerName());
        mEditMainType.setText(null);
        mEditBuiltDate.setText(null);
    }

    public void setModel(Model model) {
        this.model = model;
        mEditMainType.setText(model.getModelName());
        mEditBuiltDate.setText(null);
    }

    public void setBuiltDate(BuiltDate builtDate) {
        this.builtDate = builtDate;
        mEditBuiltDate.setText(builtDate.getYear());
    }
}
