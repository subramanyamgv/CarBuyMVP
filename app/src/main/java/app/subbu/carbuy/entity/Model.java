package app.subbu.carbuy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class Model implements MultiItemEntity, Parcelable {

    private int itemType;
    private String modelId;
    private String modelName;

    @Override
    public int getItemType() {
        return itemType;
    }

    public Model(int itemType, String modelId, String modelName) {
        this.itemType = itemType;
        this.modelId = modelId;
        this.modelName = modelName;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    protected Model(Parcel in) {
        itemType = in.readInt();
        modelId = in.readString();
        modelName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemType);
        dest.writeString(modelId);
        dest.writeString(modelName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}