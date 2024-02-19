package com.shihui.fd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserFavoriteDish;
import com.shihui.fd.entity.UserLikeDish;
import com.shihui.fd.mapper.UserFavoriteDishMapper;
import com.shihui.fd.service.IUserFavoriteDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class UserFavoriteDishServiceImpl extends ServiceImpl<UserFavoriteDishMapper, UserFavoriteDish> implements IUserFavoriteDishService {

    @Autowired
    private UserFavoriteDishMapper userFavoriteDishMapper;
    @Override
    public List<Dish> getMyFavDishes(String account,Integer startindex,Integer pagesize ) {
        return userFavoriteDishMapper.getMyFavDishes(account,startindex,pagesize);
    }

    @Override
    public boolean existsByAccountAndDishId(String account, Integer dishId) {
        QueryWrapper<UserFavoriteDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).eq("dish_id", dishId);
        return userFavoriteDishMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void addFav(String account, Integer dishId) {
        UserFavoriteDish userFavoriteDish = new UserFavoriteDish();
        userFavoriteDish.setAccount(account);
        userFavoriteDish.setDishId(dishId);
        userFavoriteDish.setCollectionTime(LocalDateTime.now());
        userFavoriteDishMapper.insert(userFavoriteDish);
    }

    @Override
    public void delFav(String account, Integer dishId) {
        QueryWrapper<UserFavoriteDish> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account).eq("dish_id",dishId);
        userFavoriteDishMapper.delete(queryWrapper);
    }
}
