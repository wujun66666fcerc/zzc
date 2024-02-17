package com.shihui.fd.mapper;

import com.shihui.common.DTO.EvaluationDTO;
import com.shihui.fd.entity.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface EvaluationMapper extends BaseMapper<Evaluation> {
    List<EvaluationDTO> selectEvaluationsWithUserByDishId(@Param("dishId") Integer dishId);

}
