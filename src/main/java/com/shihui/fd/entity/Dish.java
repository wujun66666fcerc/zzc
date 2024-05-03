package com.shihui.fd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dishId;

    private String dishName;

    private String taste;

    private String category;

    private String cuisine;

    private String ingredients;

    private Float dishPrice;

    private String imageUrl;

    private BigDecimal totalRating;

    private Integer totalLikes;

    private Integer totalFavorites;

    private String location;

    private String detailedLocation;

    private Integer storeId;
    private Boolean promotionStatus;
    private String description;


}
