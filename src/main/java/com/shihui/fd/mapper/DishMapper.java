package com.shihui.fd.mapper;

import com.shihui.fd.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface DishMapper extends BaseMapper<Dish> {

    void incrementTotalLikes(@Param("dishId") Integer dishId);


    void decrementTotalLikes(@Param("dishId")Integer dishId);

    void incrementTotalFavs(@Param("dishId")Integer dishId);

    void decrementTotalFavs(@Param("dishId")Integer dishId);
}
