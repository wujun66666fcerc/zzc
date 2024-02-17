package com.shihui.fd.controller;

import com.shihui.common.DTO.EvaluationDTO;
import com.shihui.common.vo.Result;
import com.shihui.fd.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;

    @GetMapping("/{dishId}")
    public Result<List<EvaluationDTO>> getEvaluationsByDishId(@PathVariable Integer dishId) {
        List<EvaluationDTO> evaluationsByDishId = evaluationService.getEvaluationsByDishId(dishId);
        return Result.success(evaluationsByDishId);
    }


}
