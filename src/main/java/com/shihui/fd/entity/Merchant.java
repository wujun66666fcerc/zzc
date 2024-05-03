package com.shihui.fd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    private String name;

    private String phone;

    private Integer auditStatus;
    private String auditStoreLocation;
    private String auditStoreName;



}
