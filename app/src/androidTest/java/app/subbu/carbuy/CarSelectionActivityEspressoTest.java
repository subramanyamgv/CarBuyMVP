package app.subbu.carbuy;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.subbu.carbuy.activity.CarSelectionActivity;

/**
 * Created by Subramanyam on 23-Jan-2017.
 */

@RunWith(AndroidJUnit4.class)
public class CarSelectionActivityEspressoTest {

    @Rule
    public ActivityTestRule<CarSelectionActivity> mActivityRule =
            new ActivityTestRule<>(CarSelectionActivity.class);

    @Test
    public void ensureManufacturerTextInputWorks() {

    }
}
