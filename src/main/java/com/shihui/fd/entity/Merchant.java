package com.shihui.fd.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    private String name;

    private String phone;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Merchant{" +
            "account=" + account +
            ", password=" + password +
            ", name=" + name +
            ", phone=" + phone +
        "}";
    }
}
