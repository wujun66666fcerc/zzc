package com.shihui.fd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantStoreInfo {
    // 商家信息
    private String merchantAccount;
    private String merchantName;
    private String merchantPhone;
    private Integer auditStatus;

    // 店铺信息列表
    private List<Store> stores;
}
