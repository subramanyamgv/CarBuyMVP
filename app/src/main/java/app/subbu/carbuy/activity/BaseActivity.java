package app.subbu.carbuy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import app.subbu.carbuy.CarBuyMvpApplication;
import app.subbu.carbuy.injector.component.ApplicationComponent;

/**
 * Created by Subramanyam on 21-Jan-2017.
 */

public class BaseActivity extends AppCompatActivity {

    ApplicationComponent mAppComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppComponent = ((CarBuyMvpApplication)getApplication()).getApplicationComponent();
    }
}
