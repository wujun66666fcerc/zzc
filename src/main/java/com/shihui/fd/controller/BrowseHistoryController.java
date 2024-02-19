package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.BrowseHistory;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.service.IBrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/browseHistory")
public class BrowseHistoryController {
    @Autowired
    IBrowseHistoryService browseHistoryService;
    @PostMapping("/add")
    public Result<String> addBrowserHistory(@RequestBody BrowseHistory browserHistory) {




        boolean success = browseHistoryService.addOrUpdateBrowserHistory(browserHistory);
        if (success) {
            return Result.success("Browse history added successfully");
        } else {
            return Result.fail("Failed to add browse history");
        }
    }
    @GetMapping("/myhis")
    public Result<List<Dish>> getDirectDishes(@RequestParam("pageNum")Integer pageNum,
                                              @RequestParam("pageSize")Integer pageSize,
                                              @RequestParam("account")String account) {
        Integer offset=(pageNum-1)*pageSize;
        List<Dish> bl=browseHistoryService.getBrowseHistoryByAccount(account,offset,pageSize);
        // 创建查询条件
        return Result.success(bl);
    }



}
