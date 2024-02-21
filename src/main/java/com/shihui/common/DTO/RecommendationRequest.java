package com.shihui.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    private String account;
    private int size;
    private List<Integer> displayedDishIds;
}
