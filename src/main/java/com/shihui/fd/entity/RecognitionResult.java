package com.shihui.fd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecognitionResult {
    private String category;
    private String calorie;
    private Float score;
    public RecognitionResult(String category, String calorie, float score) {
        this.category = category;
        this.calorie = calorie;
        // 格式化 score 保留三位小数
        this.score = Float.parseFloat(String.format("%.3f", score));
    }
}
