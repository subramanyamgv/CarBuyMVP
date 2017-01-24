package app.subbu.carbuy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.BuiltDate;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 23-Jan-2017.
 */

public class SummaryActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.car_type_text)
    TextView mTxtCarType;

    @BindView(R.id.main_type_text)
    TextView mTxtMainType;

    @BindView(R.id.built_date_text)
    TextView mTxtBuildDate;

    private Manufacturer mManufacturer;
    private Model mModel;
    private BuiltDate mBuiltDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mManufacturer = savedInstanceState.getParcelable(CarTypesActivity.INTENT_EXTRA);
            mModel = savedInstanceState.getParcelable(MainTypesActivity.INTENT_EXTRA);
            mBuiltDate = savedInstanceState.getParcelable(BuiltDatesActivity.INTENT_EXTRA);
        } else {
            mManufacturer = getIntent().getParcelableExtra(CarTypesActivity.INTENT_EXTRA);
            mModel = getIntent().getParcelableExtra(MainTypesActivity.INTENT_EXTRA);
            mBuiltDate = getIntent().getParcelableExtra(BuiltDatesActivity.INTENT_EXTRA);
        }

        mTxtCarType.setText(mManufacturer.getManufacturerName());
        mTxtMainType.setText(mModel.getModelName());
        mTxtBuildDate.setText(mBuiltDate.getYear());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
