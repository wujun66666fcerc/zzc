package com.shihui.fd.mapper;

import com.shihui.fd.entity.BrowseHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shihui.fd.entity.Dish;
import org.apache.ibatis.annotations.Param;
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
public interface BrowseHistoryMapper extends BaseMapper<BrowseHistory> {
    @Update("UPDATE browse_history SET browse_time = #{browseTime} WHERE account = #{account} AND dish_id = #{dishId}")
    int updateBrowseTimeByAccountAndDishId(BrowseHistory browserHistory);

    List<Dish> getBrowseHistoryByAccount(@Param("account")String account,@Param("offset") Integer offset, @Param("pageSize")Integer pageSize);
}
