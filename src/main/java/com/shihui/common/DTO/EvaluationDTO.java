package com.shihui.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private Integer evaluationId;
    private String userId;
    private Integer dishId;
    private LocalDateTime evaluationTime;
    private BigDecimal rating;
    private String comment;
    private String avatar; // 用户头像
    private String nickname; // 用户昵称


    // 省略getter和setter方法
}
