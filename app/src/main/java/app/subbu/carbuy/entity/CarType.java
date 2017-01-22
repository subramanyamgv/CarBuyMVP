package app.subbu.carbuy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarType implements MultiItemEntity, Parcelable {

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

    protected CarType(Parcel in) {
        itemType = in.readInt();
        manufacturerId = in.readString();
        manufacturerName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemType);
        dest.writeString(manufacturerId);
        dest.writeString(manufacturerName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CarType> CREATOR = new Parcelable.Creator<CarType>() {
        @Override
        public CarType createFromParcel(Parcel in) {
            return new CarType(in);
        }

        @Override
        public CarType[] newArray(int size) {
            return new CarType[size];
        }
    };
}
