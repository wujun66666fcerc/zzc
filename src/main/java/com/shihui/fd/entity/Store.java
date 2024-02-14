package com.shihui.fd.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-14
 */
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer merchantId;

    private String storeName;

    private String storeLocation;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    @Override
    public String toString() {
        return "Store{" +
            "merchantId=" + merchantId +
            ", storeName=" + storeName +
            ", storeLocation=" + storeLocation +
        "}";
    }
}
