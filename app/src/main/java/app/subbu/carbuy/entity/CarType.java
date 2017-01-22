package app.subbu.carbuy.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarType implements MultiItemEntity {

    private int itemType;
    private String manufacturerId;
    private String manufacturerName;

    public CarType(int itemType, String manufacturerId, String manufacturerName) {
        this.itemType = itemType;
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
