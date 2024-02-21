package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.common.DTO.RecommendationRequest;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;
    @PostMapping("/rec")
    public Result<List<Dish>> getRecDishes(@RequestBody RecommendationRequest request) {
        List<Dish> recommendations=dishService.getRecommendations(request);
        return Result.success(recommendations);
    }
    @GetMapping("/promoted")
    public Result<List<Dish>> getPromotedDishes() {
        // 调用 Service 层的方法查询 promotion_status 为 1 的菜品数据列表
        List<Dish> promotedDishes = dishService.getPromotedDishes();
        return Result.success(promotedDishes);
    }
    @GetMapping("/direct")
    public Result<List<Dish>> getDirectDishes(@RequestParam("pageNum")Integer pageNum,
                                              @RequestParam("pageSize")Integer pageSize,
                                              @RequestParam("name")String name) {
        Page<Dish> page = new Page<>(pageNum, pageSize);
        // 创建查询条件
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        if(name.equals("米")||name.equals("面")||name.equals("小吃")){
            queryWrapper.eq("category", name);
        }
        else{
            queryWrapper.eq("location", name);
        }
        dishService.page(page, queryWrapper);
        return Result.success(page.getRecords());
    }
    @GetMapping("/search")
    public Result<List<Dish>> getSearchDishes(@RequestParam("pageNum")Integer pageNum,
                                              @RequestParam("pageSize")Integer pageSize,
                                              @RequestParam("value")String value) {
        Page<Dish> page = new Page<>(pageNum, pageSize);
        // 创建查询条件
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("dish_name", value)
                .or()
                .like("taste", value)
                .or()
                .like("category", value)
                .or()
                .like("cuisine", value)
                .or()
                .like("ingredients", value)
                .or()
                .like("detailed_location", value)
                .or()
                .like("description",value);
        dishService.page(page, queryWrapper);
        return Result.success(page.getRecords());
    }

    private Integer matchesCondition(Dish dish, String condition) {
        switch (condition) {
            case "6":
                return dish.getTaste().contains("酸") ? 1 : 0;
            case "7":
                return dish.getTaste().contains("甜") ? 1 : 0;
            case "8":
                return dish.getTaste().contains("咸") ? 1 : 0;
            case "9":
                return dish.getTaste().contains("辣") ? 1 : 0;
            case "10":
                return dish.getTaste().contains("清淡") ? 1 : 0;
            case "11":
                return dish.getCategory().contains("米") ? 1 : 0;
            case "12":
                return dish.getCategory().contains("面") ? 1 : 0;
            case "13":
                return dish.getCategory().contains("汤") ? 1 : 0;
            case "14":
                return dish.getCategory().contains("饼") ? 1 : 0;
            case "15":
                return dish.getCategory().contains("粥") ? 1 : 0;
            case "16":
                return dish.getCategory().contains("小吃") ? 1 : 0;
            default:
                return 0;
        }
    }


    @GetMapping("/filter")
    public Result<List<Dish>> filterDishes(@RequestParam("activeId") String conditions,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize){
        List<Dish> result = dishService.filterDishes(conditions);
        String[] conditionArray = conditions.split(",");
        Map<Dish, Integer> dishScoreMap = new HashMap<>();
        for (Dish dish : result) {
            int score = 0;
            for (String condition : conditionArray) {
                //System.out.println("此时condition为"+condition+",菜品口味为"+dish.getTaste());
                score+=matchesCondition(dish, condition);
            }
            // 将菜品和分数放入映射
            dishScoreMap.put(dish, score);
        }
        List<Dish> sortedResult = result.stream()
                .sorted((dish1, dish2) -> {
                    Integer score1 = dishScoreMap.getOrDefault(dish1, 0);
                    Integer score2 = dishScoreMap.getOrDefault(dish2, 0);
                    return score2.compareTo(score1); // 降序排序
                })
                .collect(Collectors.toList());
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, result.size());

       // 当起始位置大于数据总条数时，返回空列表
        if (startIndex >= result.size()) {
            return Result.success(Collections.emptyList());
        }
        sortedResult = sortedResult.subList(startIndex, endIndex);
        return Result.success(sortedResult);
    }




}
