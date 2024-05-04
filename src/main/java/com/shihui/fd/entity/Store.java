package com.shihui.fd.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer storeId;

    private String storeName;

    private String storeLocation;

    private String location;
}
