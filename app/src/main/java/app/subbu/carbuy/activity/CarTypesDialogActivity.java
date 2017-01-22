package app.subbu.carbuy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.subbu.carbuy.R;
import app.subbu.carbuy.fragment.CarTypesFragment;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.injector.component.DaggerCarSelectionComponent;
import app.subbu.carbuy.injector.module.CarSelectionModule;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarTypesDialogActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private CarSelectionComponent carSelectionComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carSelectionComponent = DaggerCarSelectionComponent.builder()
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
        return carSelectionComponent;
    }
}
