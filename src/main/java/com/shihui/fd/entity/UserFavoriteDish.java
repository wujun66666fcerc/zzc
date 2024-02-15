package com.shihui.fd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@TableName("user_favorite_dish")
public class UserFavoriteDish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String account;

    private Integer dishId;

    private LocalDateTime collectionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
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
    public LocalDateTime getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(LocalDateTime collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "UserFavoriteDish{" +
            "id=" + id +
            ", account=" + account +
            ", dishId=" + dishId +
            ", collectionTime=" + collectionTime +
        "}";
    }
}
