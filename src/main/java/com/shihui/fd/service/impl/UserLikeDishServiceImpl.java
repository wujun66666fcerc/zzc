package com.shihui.fd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserLikeDish;
import com.shihui.fd.mapper.UserLikeDishMapper;
import com.shihui.fd.service.IUserLikeDishService;
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
public class UserLikeDishServiceImpl extends ServiceImpl<UserLikeDishMapper, UserLikeDish> implements IUserLikeDishService {


    @Autowired
    private UserLikeDishMapper userLikeDishMapper;
    @Override
    public boolean existsByAccountAndDishId(String account, Integer dishId) {
        QueryWrapper<UserLikeDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).eq("dish_id", dishId);
        return userLikeDishMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void addLike(String account, Integer dishId) {
        UserLikeDish userLikeDish = new UserLikeDish();
        userLikeDish.setAccount(account);
        userLikeDish.setDishId(dishId);
        userLikeDish.setLikeTime(LocalDateTime.now());
        userLikeDishMapper.insert(userLikeDish);
    }

    @Override
    public void delLike(String account, Integer dishId) {
        QueryWrapper<UserLikeDish> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",account).eq("dish_id",dishId);
        userLikeDishMapper.delete(queryWrapper);
    }

    @Override
    public List<Dish> getLikedDishesByAccount(String account) {
        return userLikeDishMapper.getLikedDishesByAccount(account);
    }
}
