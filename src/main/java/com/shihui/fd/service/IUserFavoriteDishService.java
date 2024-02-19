package com.shihui.fd.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserFavoriteDish;
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
public interface IUserFavoriteDishService extends IService<UserFavoriteDish> {

    List<Dish> getMyFavDishes( String account,Integer startindex,Integer pagesize);

    boolean existsByAccountAndDishId(String account, Integer dishId);

    void addFav(String account, Integer dishId);

    void delFav(String account, Integer dishId);
}
