package com.shihui.common.DTO;

import com.shihui.fd.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {
    private static final long serialVersionUID = 1L;
    private String merchantId;
    private Store store;

    // Getters and setters
    // Constructors
}
