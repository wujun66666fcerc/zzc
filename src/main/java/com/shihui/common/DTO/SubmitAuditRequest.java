package com.shihui.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 请求参数类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitAuditRequest {

    private String code;
    private String merchantName;
    private String phone;
    private String storeName;
    private String storeLocation;

    // 省略 getter 和 setter 方法
}
