package com.shihui.fd.controller;

import com.shihui.common.vo.Result;
import com.shihui.fd.entity.User;
import com.shihui.fd.service.IDishService;
import com.shihui.fd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list);
    }

}
