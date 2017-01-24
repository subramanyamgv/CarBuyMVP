package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.fragment.CarTypesFragment;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.injector.component.DaggerCarSelectionComponent;
import app.subbu.carbuy.injector.module.CarSelectionModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarTypesActivity extends BaseActivity implements EntitySelectionListener<Manufacturer> {

    public static final String INTENT_EXTRA = "manufacturer";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CarSelectionComponent mCarSelectionComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCarSelectionComponent = DaggerCarSelectionComponent.builder()
                .applicationComponent(mAppComponent)
                .carSelectionModule(new CarSelectionModule())
                .build();

        CarTypesFragment.start(R.id.container, getSupportFragmentManager());
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
    public void onEntitySelected(Manufacturer data) {
        Intent intent = new Intent(getApplicationContext(), MainTypesActivity.class);
        intent.putExtra(CarTypesActivity.INTENT_EXTRA, data);

        startActivity(intent);
    }
}
