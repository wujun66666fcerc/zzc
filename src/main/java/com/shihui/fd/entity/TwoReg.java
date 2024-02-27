package com.shihui.fd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoReg {
    private List<Dish> categories;
    private List<RecognitionResult> recognitionResults;
}
