package com.shihui.fd.service;

import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserLikeDish;
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
public interface IUserLikeDishService extends IService<UserLikeDish> {

    boolean existsByAccountAndDishId(String account, Integer dishId);

    void addLike(String account, Integer dishId);

    void delLike(String account, Integer dishId);

    List<Dish> getLikedDishesByAccount(String account);

}
