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
 * @date 2019-07-15.
 * module：
 * description：
 */
@Keep
public class ProductData {

    private long id;

    private String code;

    private boolean specTag;

    private String operatorId;

    private String shopId;

    private String name;

    private int categoryId;

    private String summary;

    private String label;

    private boolean discountFlag;

    private int houseArea;

    private boolean sellTimeLimitFlag;

    private String sellTimeStart;

    private String sellTimeEnd;

    private String contractId;

    private int sellAmount;

    private int status;

    private List<String> lables;

    private List<String> pictureUrls;

    private String pictureUrl;

    private int maxPrice;

    private int minPrice;

    private List<Sku> skus;

    private String attrs;

    private String detailLink;

    private List<String> houseAreas;

    private String statusName;

    private String categoryName;

    private String attrsStr;

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


    public String getAttrsStr() {
        return attrsStr;
    }

    public void setAttrsStr(String attrsStr) {
        this.attrsStr = attrsStr;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSpecTag() {
        return specTag;
    }

    public void setSpecTag(boolean specTag) {
        this.specTag = specTag;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
    }

    public int getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(int houseArea) {
        this.houseArea = houseArea;
    }

    public boolean isSellTimeLimitFlag() {
        return sellTimeLimitFlag;
    }

    public void setSellTimeLimitFlag(boolean sellTimeLimitFlag) {
        this.sellTimeLimitFlag = sellTimeLimitFlag;
    }

    public String getSellTimeStart() {
        return sellTimeStart;
    }

    public void setSellTimeStart(String sellTimeStart) {
        this.sellTimeStart = sellTimeStart;
    }

    public String getSellTimeEnd() {
        return sellTimeEnd;
    }

    public void setSellTimeEnd(String sellTimeEnd) {
        this.sellTimeEnd = sellTimeEnd;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public int getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getLables() {
        return lables;
    }

    public void setLables(List<String> lables) {
        this.lables = lables;
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

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public List<String> getHouseAreas() {
        return houseAreas;
    }

    public void setHouseAreas(List<String> houseAreas) {
        this.houseAreas = houseAreas;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }
}
