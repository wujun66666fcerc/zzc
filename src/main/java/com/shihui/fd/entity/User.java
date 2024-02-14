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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private String password;

    private String nickname;

    private String avatar;

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
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
            "account=" + account +
            ", password=" + password +
            ", nickname=" + nickname +
            ", avatar=" + avatar +
        "}";
    }
}
