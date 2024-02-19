package com.shihui.fd.service.impl;

import com.shihui.common.DTO.EvaluationDTO;
import com.shihui.common.DTO.EvaluationListDTO;
import com.shihui.fd.entity.Evaluation;
import com.shihui.fd.mapper.EvaluationMapper;
import com.shihui.fd.service.IEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements IEvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public List<EvaluationDTO> getEvaluationsByDishId(Integer dishId) {

        return evaluationMapper.selectEvaluationsWithUserByDishId(dishId);
    }

    @Override
    public List<EvaluationListDTO> getEvaluationsByUserId(Integer offset,Integer pageSize,String account) {
        return evaluationMapper.getMyEvaluations(offset,pageSize,account);
    }

    @Override
    public void postEvaluation(Evaluation evaluation) {
        System.out.println(evaluation.getDishId()+"11111"+evaluation.getUserId());
        if (evaluationMapper.existsByDishIdAndAccount(evaluation.getDishId(), evaluation.getUserId())>0) {
            // 如果已存在评价记录，则更新评价信息
            evaluationMapper.updateEvaluation(evaluation);
        } else {
            // 否则插入新的评价记录
            evaluationMapper.insertEvaluation(evaluation);
        }
        // 更新菜品平均评分
        evaluationMapper.updateDishTotalRating(evaluation.getDishId());
    }


}
