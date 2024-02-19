package com.shihui.fd.service;

import com.shihui.common.DTO.EvaluationListDTO;
import com.shihui.fd.entity.Evaluation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shihui.common.DTO.EvaluationDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface IEvaluationService extends IService<Evaluation> {
    List<EvaluationDTO> getEvaluationsByDishId(Integer dishId);


    List<EvaluationListDTO> getEvaluationsByUserId(Integer offset,Integer pageSize,String account);


    void postEvaluation(Evaluation evaluation);
}
