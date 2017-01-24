package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.entity.Model;
import app.subbu.carbuy.fragment.MainTypesFragment;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.injector.component.DaggerCarSelectionComponent;
import app.subbu.carbuy.injector.module.CarSelectionModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class MainTypesActivity extends BaseActivity implements EntitySelectionListener<Model> {

    public static final String INTENT_EXTRA = "model";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CarSelectionComponent mCarSelectionComponent;
    private Manufacturer mManufacturer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            mManufacturer = savedInstanceState.getParcelable(CarTypesActivity.INTENT_EXTRA);
        } else {
            mManufacturer = getIntent().getParcelableExtra(CarTypesActivity.INTENT_EXTRA);
        }

        getSupportActionBar().setSubtitle(mManufacturer.getManufacturerName());

        mCarSelectionComponent = DaggerCarSelectionComponent.builder()
                .applicationComponent(mAppComponent)
                .carSelectionModule(new CarSelectionModule())
                .build();

        MainTypesFragment.start(R.id.container, getSupportFragmentManager(), mManufacturer);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CarTypesActivity.INTENT_EXTRA, mManufacturer);
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
    public void onEntitySelected(Model data) {
        //Send Manufacturer and Model
        Intent intent = new Intent(getApplicationContext(), BuiltDatesActivity.class);
        intent.putExtra(CarTypesActivity.INTENT_EXTRA, mManufacturer);
        intent.putExtra(MainTypesActivity.INTENT_EXTRA, data);

        startActivity(intent);
    }
}
