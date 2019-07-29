package com.madreain.sku.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

import java.util.List;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
@Keep
public class Sku implements Parcelable {

    private long id;

    private int commodityId;

    private String spec;

    private int price;

    private String pictureId;

    private String pictureUrl;

    private List<SkuAttribute> attributes;

    private int stockQuantity;

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<SkuAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SkuAttribute> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", spec='" + spec + '\'' +
                ", price=" + price +
                ", pictureId='" + pictureId + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public Sku() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.commodityId);
        dest.writeString(this.spec);
        dest.writeInt(this.price);
        dest.writeString(this.pictureId);
        dest.writeString(this.pictureUrl);
        dest.writeTypedList(this.attributes);
    }

    protected Sku(Parcel in) {
        this.id = in.readInt();
        this.commodityId = in.readInt();
        this.spec = in.readString();
        this.price = in.readInt();
        this.pictureId = in.readString();
        this.pictureUrl = in.readString();
        this.attributes = in.createTypedArrayList(SkuAttribute.CREATOR);
    }

    public static final Creator<Sku> CREATOR = new Creator<Sku>() {
        @Override
        public Sku createFromParcel(Parcel source) {
            return new Sku(source);
        }

        @Override
        public Sku[] newArray(int size) {
            return new Sku[size];
        }
    };


}
