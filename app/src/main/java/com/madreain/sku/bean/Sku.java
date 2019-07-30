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

    private long sid;

    private float price;

    private String pictureUrl;

    private List<SkuAttribute> attributes;

    private int stockQuantity;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
                "sid=" + sid +
                ", price=" + price +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", attributes=" + attributes +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    public Sku() {

    }

    /**
     * 描述
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 反序列化
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.sid);
        dest.writeFloat(this.price);
        dest.writeString(this.pictureUrl);
        dest.writeTypedList(this.attributes);
    }

    /**
     * 序列化
     * @param in
     */
    protected Sku(Parcel in) {
        this.sid = in.readLong();
        this.price = in.readFloat();
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
