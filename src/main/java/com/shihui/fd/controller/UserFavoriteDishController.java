package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserFavoriteDish;
import com.shihui.fd.entity.UserLikeDish;
import com.shihui.fd.service.IDishService;
import com.shihui.fd.service.IUserFavoriteDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/userFavoriteDish")
public class UserFavoriteDishController {
    @Autowired
    IUserFavoriteDishService userFavoriteDishService;
    @Autowired
    IDishService dishService;
    @GetMapping("/list")
    public Result<List<Dish>> getMyFavDishes(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize,
                                             @RequestParam("account") String account) {
        Integer starindex=(pageNum-1)*pageSize;
        List<Dish> dishes = userFavoriteDishService.getMyFavDishes( account,starindex,pageSize);
        return Result.success(dishes);
    }

    @GetMapping("/checkFav")
    public ResponseEntity<String> checkLike(@RequestParam("account") String account,
                                            @RequestParam("dishId") Integer dishId) {
        try {
            // 检查用户是否已经收藏过该菜品
            boolean hasLiked = userFavoriteDishService.existsByAccountAndDishId(account, dishId);

            // 返回点赞状态给前端
            if (hasLiked) {
                return ResponseEntity.ok("已收藏");
            } else {
                return ResponseEntity.ok("未收藏");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询收藏状态失败");
        }
    }
    @PostMapping("/addFav")
    public ResponseEntity<String> addLike(@RequestBody UserFavoriteDish userFavoriteDish) {
        String account=userFavoriteDish.getAccount();
        Integer dishId=userFavoriteDish.getDishId();
        try {
            // 检查是否已经点赞过
            if (userFavoriteDishService.existsByAccountAndDishId(account, dishId)) {
                return ResponseEntity.badRequest().body("您已经收藏过了");
            }

            // 向 user_like_dish 表插入点赞记录
            userFavoriteDishService.addFav(account, dishId);

            // 更新 dish 表的 total_likes 字段
            dishService.incrementTotalFavs(dishId);

            return ResponseEntity.ok("收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("收藏失败");
        }
    }
    @PostMapping("/deFav")
    public ResponseEntity<String> delLike(@RequestBody UserFavoriteDish userFavoriteDish) {
        String account=userFavoriteDish.getAccount();
        Integer dishId=userFavoriteDish.getDishId();
        try {
            // 检查是否已经点赞过
            if (!userFavoriteDishService.existsByAccountAndDishId(account, dishId)) {
                return ResponseEntity.badRequest().body("用户未收藏该菜品！");
            }

            // 向 user_like_dish 表插入点赞记录
            userFavoriteDishService.delFav(account, dishId);

            // 更新 dish 表的 total_likes 字段
            dishService.decrementTotalFavs(dishId);

            return ResponseEntity.ok("取消收藏成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("取消收藏失败");
        }
    }

}
