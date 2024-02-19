package com.shihui.fd.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserFavoriteDish;
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
public interface UserFavoriteDishMapper extends BaseMapper<UserFavoriteDish> {

    List<Dish> getMyFavDishes(@Param("account") String account, @Param("startindex") Integer startindex, @Param("pagesize") Integer pagesize);
}
