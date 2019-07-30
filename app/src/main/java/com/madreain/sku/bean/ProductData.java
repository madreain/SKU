package com.madreain.sku.bean;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.madreain.sku.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
@Keep
public class ProductData {

    //主图url
    private String pictureUrl;

    //最高价格
    private int maxPrice;

    //最低价格
    private int minPrice;

    //sku属性
    private List<Sku> skus;

    //数量
    private int stockQuantity;

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public static ProductData get(Context context) {
        String json = readTextFromSDcard(context);
        return new Gson().fromJson(json, new TypeToken<ProductData>() {
        }.getType());
    }

    private static String readTextFromSDcard(Context context) {
        String resultString = null;
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(context.getAssets().open("Product.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            resultString = stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

}
