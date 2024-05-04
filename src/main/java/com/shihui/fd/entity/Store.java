package com.shihui.fd.entity;

import com.baomidou.mybatisplus.annotation.TableId;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer storeId;

    private String storeName;

    private String storeLocation;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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
            "storeId=" + storeId +
            ", storeName=" + storeName +
            ", storeLocation=" + storeLocation +
        "}";
    }
}
