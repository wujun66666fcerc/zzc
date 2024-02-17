package com.shihui.fd.entity;

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

    public Boolean getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(Boolean promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public Float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Float dishPrice) {
        this.dishPrice = dishPrice;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public BigDecimal getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(BigDecimal totalRating) {
        this.totalRating = totalRating;
    }
    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }
    public Integer getTotalFavorites() {
        return totalFavorites;
    }

    public void setTotalFavorites(Integer totalFavorites) {
        this.totalFavorites = totalFavorites;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getDetailedLocation() {
        return detailedLocation;
    }

    public void setDetailedLocation(String detailedLocation) {
        this.detailedLocation = detailedLocation;
    }
    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Dish{" +
            "dishId=" + dishId +
            ", dishName=" + dishName +
            ", taste=" + taste +
            ", category=" + category +
            ", cuisine=" + cuisine +
            ", ingredients=" + ingredients +
            ", dishPrice=" + dishPrice +
            ", imageUrl=" + imageUrl +
            ", totalRating=" + totalRating +
            ", totalLikes=" + totalLikes +
            ", totalFavorites=" + totalFavorites +
            ", location=" + location +
            ", detailedLocation=" + detailedLocation +
            ", storeId=" + storeId +
        "}";
    }
}
