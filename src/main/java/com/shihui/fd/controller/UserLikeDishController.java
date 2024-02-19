package com.shihui.fd.controller;

import com.shihui.fd.entity.UserLikeDish;
import com.shihui.fd.service.IDishService;
import com.shihui.fd.service.IUserLikeDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/userLikeDish")
public class UserLikeDishController {
    @Autowired
    private IUserLikeDishService userLikeDishService;

    @Autowired
    private IDishService dishService;
    @GetMapping("/checkLike")
    public ResponseEntity<String> checkLike(@RequestParam("account") String account,
                                            @RequestParam("dishId") Integer dishId) {
        try {
            // 检查用户是否已经点赞过该菜品
            boolean hasLiked = userLikeDishService.existsByAccountAndDishId(account, dishId);

            // 返回点赞状态给前端
            if (hasLiked) {
                return ResponseEntity.ok("已点赞");
            } else {
                return ResponseEntity.ok("未点赞");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询点赞状态失败");
        }
    }
    @PostMapping("/addLike")
    public ResponseEntity<String> addLike(@RequestBody UserLikeDish userLikeDish) {
        String account=userLikeDish.getAccount();
        Integer dishId=userLikeDish.getDishId();
        try {
            // 检查是否已经点赞过
            if (userLikeDishService.existsByAccountAndDishId(account, dishId)) {
                return ResponseEntity.badRequest().body("您已经点赞过了");
            }

            // 向 user_like_dish 表插入点赞记录
            userLikeDishService.addLike(account, dishId);

            // 更新 dish 表的 total_likes 字段
            dishService.incrementTotalLikes(dishId);

            return ResponseEntity.ok("点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("点赞失败");
        }
    }
    @PostMapping("/deLike")
    public ResponseEntity<String> delLike(@RequestBody UserLikeDish userLikeDish) {
        String account=userLikeDish.getAccount();
        Integer dishId=userLikeDish.getDishId();
        try {
            // 检查是否已经点赞过
            if (!userLikeDishService.existsByAccountAndDishId(account, dishId)) {
                return ResponseEntity.badRequest().body("用户未点赞该菜品！");
            }

            // 向 user_like_dish 表插入点赞记录
            userLikeDishService.delLike(account, dishId);

            // 更新 dish 表的 total_likes 字段
            dishService.decrementTotalLikes(dishId);

            return ResponseEntity.ok("取消点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("取消点赞失败");
        }
    }


}
