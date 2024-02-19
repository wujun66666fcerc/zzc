package com.shihui.fd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.mapper.DishMapper;
import com.shihui.fd.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<Dish> listTopDishes(int limit) {
        QueryWrapper<Dish> wrapper=new QueryWrapper<>();
        wrapper.last("limit "+limit);
        return baseMapper.selectList(wrapper);

    }

    @Override
    public List<Dish> getPromotedDishes() {
        // 构建查询条件，查询 promotion_status 为 1 的菜品数据列表
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("promotion_status", 1);
        List<Dish> promotedDishes = dishMapper.selectList(queryWrapper);
        return promotedDishes;
    }
    // 区域、口味、种类、风味菜系和忌口的映射关系
    private static final Map<Integer, String> VALUE_MAP = new HashMap<>();

    static {
        // 初始化映射关系
        VALUE_MAP.put(1, "松园");
        VALUE_MAP.put(2, "菊园");
        VALUE_MAP.put(3, "荷园");
        VALUE_MAP.put(4, "柳园");
        VALUE_MAP.put(5, "商业街");
        VALUE_MAP.put(6, "酸");
        VALUE_MAP.put(7, "甜");
        VALUE_MAP.put(8, "咸");
        VALUE_MAP.put(9, "辣");
        VALUE_MAP.put(10, "清淡");
        VALUE_MAP.put(11, "米");
        VALUE_MAP.put(12, "面");
        VALUE_MAP.put(13, "汤");
        VALUE_MAP.put(14, "饼");
        VALUE_MAP.put(15, "粥");
        VALUE_MAP.put(16, "小吃");
        VALUE_MAP.put(17, "浙菜");
        VALUE_MAP.put(18, "湘菜");
        VALUE_MAP.put(19, "川菜");
        VALUE_MAP.put(20, "豫菜");
        VALUE_MAP.put(21, "粤菜");
        VALUE_MAP.put(22, "淮扬菜");
        VALUE_MAP.put(23, "外国菜");
        VALUE_MAP.put(24, "其他");
        VALUE_MAP.put(25, "猪肉");
        VALUE_MAP.put(26, "牛肉");
        VALUE_MAP.put(27, "羊肉");
        VALUE_MAP.put(28, "鱼肉");
        VALUE_MAP.put(29, "鸡肉");
        VALUE_MAP.put(30, "鸭肉");
    }
    private String mapValue(int value) {
        return VALUE_MAP.getOrDefault(value, "");
    }

    @Override
    public List<Dish> filterDishes(String conditions) {


        String[] conditionArray = conditions.split(",");

        // 构建查询条件
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();

        // 区域条件集合
        List<String> locationConditions = new ArrayList<>();
        // 口味条件集合
        List<String> tasteConditions = new ArrayList<>();
        // 种类条件集合
        List<String> categoryConditions = new ArrayList<>();
        // 风味菜系条件集合
        List<String> cuisineConditions = new ArrayList<>();
        // 忌口条件集合
        List<String> ingredientConditions = new ArrayList<>();

        // 遍历条件，根据类型放入对应的集合中
        for (String condition : conditionArray) {
            int value = Integer.parseInt(condition);
            switch (value) {
                case 1: case 2: case 3: case 4: case 5:
                    locationConditions.add(mapValue(value));
                    break;
                case 6: case 7: case 8: case 9: case 10:
                    tasteConditions.add(mapValue(value));
                    break;
                case 11: case 12: case 13: case 14: case 15: case 16:
                    categoryConditions.add(mapValue(value));
                    break;
                case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24:
                    cuisineConditions.add(mapValue(value));
                    break;
                case 25: case 26: case 27: case 28: case 29: case 30:
                    ingredientConditions.add(mapValue(value));
                    break;
                default:
                    // 其他情况，可以忽略或抛出异常
            }
        }
        if (!locationConditions.isEmpty()){
            queryWrapper.and(wrapper -> {
                for (String location : locationConditions) {
                    wrapper.or().like("location", location);
                }
            });
        }

        if (!tasteConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String taste : tasteConditions) {
                    wrapper.or().like("taste", taste);
                }
            });
        }

        if (!categoryConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String category : categoryConditions) {
                    wrapper.or().like("category", category);
                }
            });
        }

        if (!cuisineConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String cuisine : cuisineConditions) {
                    wrapper.or().like("cuisine", cuisine);
                }
            });
        }
        // 忌口条件
        if (!ingredientConditions.isEmpty()) {
            queryWrapper.and(wrapper -> ingredientConditions.forEach(ingredient -> wrapper.notLike("ingredients", ingredient)));
        }

        // 查询数据
        List<Dish> result = list(queryWrapper);



        return result;
    }

    @Override
    public void incrementTotalLikes(Integer dishId) {
        dishMapper.incrementTotalLikes(dishId);
    }

    @Override
    public void decrementTotalLikes(Integer dishId) {
        dishMapper.decrementTotalLikes(dishId);
    }

    @Override
    public void incrementTotalFavs(Integer dishId) {
        dishMapper.incrementTotalFavs(dishId);
    }

    @Override
    public void decrementTotalFavs(Integer dishId) {
        dishMapper.decrementTotalFavs(dishId);
    }


}
