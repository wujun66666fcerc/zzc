package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.common.DTO.EvaluationDTO;
import com.shihui.common.DTO.EvaluationListDTO;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Evaluation;
import com.shihui.fd.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
    @GetMapping("/list")
    public Result<List<EvaluationListDTO>> getEvaluationsByUserId(@RequestParam("pageNum") Integer pageNum,
                                                                  @RequestParam("pageSize") Integer pageSize,
                                                                  @RequestParam(value="account") String account) {
        Integer offset=(pageNum-1)*pageSize;
        List<EvaluationListDTO> evaluationsByUserId = evaluationService.getEvaluationsByUserId(offset,pageSize,account);
        return Result.success(evaluationsByUserId);
    }

    @GetMapping("/getEva")
    public Result<Evaluation> getEvaluation(@RequestParam("dishId") Integer dishId,
                                            @RequestParam("account") String account) {
        QueryWrapper<Evaluation> evaluationQueryWrapper = new QueryWrapper<>();
        evaluationQueryWrapper.eq("dish_id",dishId).eq("user_id",account);
        Evaluation one = evaluationService.getOne(evaluationQueryWrapper);
        return  Result.success(one);


    }
    @PostMapping("/postEva")
    public ResponseEntity<String> postEvaluation(@RequestBody Evaluation evaluation) {
        try {
            evaluationService.postEvaluation(evaluation);
            return ResponseEntity.ok("评论成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("评论失败");
        }
    }


}
