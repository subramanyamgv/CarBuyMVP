package app.subbu.carbuy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class BuiltDate implements MultiItemEntity, Parcelable {

    private int itemType;
    private String year;

    @Override
    public int getItemType() {
        return itemType;
    }

    public BuiltDate(int itemType, String year) {
        this.itemType = itemType;
        this.year = year;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    protected BuiltDate(Parcel in) {
        itemType = in.readInt();
        year = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemType);
        dest.writeString(year);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BuiltDate> CREATOR = new Parcelable.Creator<BuiltDate>() {
        @Override
        public BuiltDate createFromParcel(Parcel in) {
            return new BuiltDate(in);
        }

        @Override
        public BuiltDate[] newArray(int size) {
            return new BuiltDate[size];
        }
    };
}