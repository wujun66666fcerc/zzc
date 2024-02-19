package com.shihui.common.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationListDTO {
    private Integer evaluationId;
    private String userId;
    private Integer dishId;
    private LocalDateTime evaluationTime;
    private BigDecimal rating;
    private String comment;
    private String avatar; // 用户头像
    private String nickname; // 用户昵称
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
    private String description;


}
