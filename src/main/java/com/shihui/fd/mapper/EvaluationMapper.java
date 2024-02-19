package com.shihui.fd.mapper;

import com.shihui.common.DTO.EvaluationDTO;
import com.shihui.common.DTO.EvaluationListDTO;
import com.shihui.fd.entity.Evaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    List<EvaluationListDTO> getMyEvaluations(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("account") String account);
    @Select("SELECT COUNT(*) FROM evaluation WHERE dish_id = #{dishId} AND user_id = #{userId}")
    int existsByDishIdAndAccount(@Param("dishId") Integer dishId, @Param("userId") String userId);
    @Update("UPDATE evaluation SET rating = #{rating}, comment = #{comment}, evaluation_time = #{evaluationTime} " +
            "WHERE dish_id = #{dishId} AND user_id = #{userId}")
    void updateEvaluation(Evaluation evaluation);
    @Insert("INSERT INTO evaluation (dish_id, user_id, rating, comment, evaluation_time) " +
            "VALUES (#{dishId}, #{userId}, #{rating}, #{comment}, #{evaluationTime})")
    void insertEvaluation(Evaluation evaluation);
    @Update("UPDATE dish SET total_rating = (SELECT AVG(rating) FROM evaluation WHERE dish_id = #{dishId}) " +
            "WHERE dish_id = #{dishId}")
    void updateDishTotalRating(Integer dishId);
}
