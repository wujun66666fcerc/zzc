package com.shihui.fd.service.impl;

import com.shihui.common.DTO.EvaluationDTO;
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
}
