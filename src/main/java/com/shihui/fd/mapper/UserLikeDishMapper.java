package com.shihui.fd.mapper;

import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserLikeDish;
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
public interface UserLikeDishMapper extends BaseMapper<UserLikeDish> {

    List<Dish> getLikedDishesByAccount(@Param("account")String account);

}
