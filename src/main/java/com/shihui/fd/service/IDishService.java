package com.shihui.fd.service;

import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface IDishService extends IService<Dish> {
    List<Dish> listTopDishes(int limit);

    List<Dish> getPromotedDishes();

    List<Dish> filterDishes(String conditions);
}
