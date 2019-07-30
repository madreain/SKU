package com.madreain.sku.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
@Keep
public class SkuAttribute implements Parcelable {

    private String key;

    private String value;

    public SkuAttribute() {
    }

    public SkuAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SkuAttribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
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
        dest.writeString(this.key);
        dest.writeString(this.value);
    }

    /**
     * 序列化
     * @param in
     */
    protected SkuAttribute(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public static final Creator<SkuAttribute> CREATOR = new Creator<SkuAttribute>() {
        @Override
        public SkuAttribute createFromParcel(Parcel source) {
            return new SkuAttribute(source);
        }

        @Override
        public SkuAttribute[] newArray(int size) {
            return new SkuAttribute[size];
        }
    };

}
