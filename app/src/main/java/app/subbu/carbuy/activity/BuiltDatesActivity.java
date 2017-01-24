package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.BuiltDate;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import app.subbu.carbuy.fragment.BuiltDatesFragment;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.injector.component.DaggerCarSelectionComponent;
import app.subbu.carbuy.injector.module.CarSelectionModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class BuiltDatesActivity extends BaseActivity implements EntitySelectionListener<BuiltDate> {

    public static final String INTENT_EXTRA = "year";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CarSelectionComponent mCarSelectionComponent;
    private Manufacturer mManufacturer;
    private Model mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mManufacturer = savedInstanceState.getParcelable(CarTypesActivity.INTENT_EXTRA);
            mModel = savedInstanceState.getParcelable(MainTypesActivity.INTENT_EXTRA);
        } else {
            mManufacturer = getIntent().getParcelableExtra(CarTypesActivity.INTENT_EXTRA);
            mModel = getIntent().getParcelableExtra(MainTypesActivity.INTENT_EXTRA);
        }

        getSupportActionBar().setSubtitle(mManufacturer.getManufacturerName() + " | " + mModel.getModelName());

        mCarSelectionComponent = DaggerCarSelectionComponent.builder()
                .applicationComponent(mAppComponent)
                .carSelectionModule(new CarSelectionModule())
                .build();

        BuiltDatesFragment.start(R.id.container, getSupportFragmentManager(), mManufacturer, mModel);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CarTypesActivity.INTENT_EXTRA, mManufacturer);
        outState.putParcelable(MainTypesActivity.INTENT_EXTRA, mModel);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public CarSelectionComponent getCarSelectionComponent() {
        return mCarSelectionComponent;
    }

    @Override
    public void onEntitySelected(BuiltDate data) {

        Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
        intent.putExtra(CarTypesActivity.INTENT_EXTRA, mManufacturer);
        intent.putExtra(MainTypesActivity.INTENT_EXTRA, mModel);
        intent.putExtra(BuiltDatesActivity.INTENT_EXTRA, data);

        startActivity(intent);
    }
}
