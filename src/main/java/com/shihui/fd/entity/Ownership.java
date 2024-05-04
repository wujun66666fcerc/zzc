package com.shihui.fd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@AllArgsConstructor
@NoArgsConstructor
public class Ownership implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ownership_id", type = IdType.AUTO)
    private Integer ownershipId;

    private String merchantAccount;

    private Integer storeId;


    public Integer getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(Integer ownershipId) {
        this.ownershipId = ownershipId;
    }
    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Ownership{" +
            "ownershipId=" + ownershipId +
            ", merchantAccount=" + merchantAccount +
            ", storeId=" + storeId +
        "}";
    }
}
