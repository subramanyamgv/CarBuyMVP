package app.subbu.carbuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import app.subbu.carbuy.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.btn_buy_car)
    public void OnBuyCarPresssed() {
        startActivity(new Intent(getApplicationContext(), CarTypesActivity.class));
    }
}
