package app.subbu.carbuy.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import app.subbu.carbuy.R;
import app.subbu.carbuy.entity.CarType;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarTypesListAdapter extends BaseMultiItemQuickAdapter<CarType, BaseViewHolder> {

    public static final int EVEN = 0;
    public static final int ODD = 1;

    public CarTypesListAdapter(List<CarType> data) {
        super(data);
        addItemType(EVEN, R.layout.list_item);
        addItemType(ODD, R.layout.list_item2);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarType item) {
        helper.setText(android.R.id.text1, item.getManufacturerName());
    }
}
