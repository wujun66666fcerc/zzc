package com.shihui.fd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-14
 */
@TableName("browse_history")
public class BrowseHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;

    private Integer dishId;

    private LocalDateTime browseTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }
    public LocalDateTime getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(LocalDateTime browseTime) {
        this.browseTime = browseTime;
    }

    @Override
    public String toString() {
        return "BrowseHistory{" +
            "account=" + account +
            ", dishId=" + dishId +
            ", browseTime=" + browseTime +
        "}";
    }
}
