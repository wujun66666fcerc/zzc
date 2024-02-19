package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shihui.common.Utils.CosUtil;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.User;
import com.shihui.fd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/login")
    public ResponseEntity<String> getcode(@RequestParam(value = "code")String  code){
        //System.out.println(code);
        ResponseEntity<String> codetoopenid = userService.codetoopenid(code);
        return codetoopenid;

    }


    private final CosUtil cosUtil;
    public UserController(CosUtil cosUtil){
        this.cosUtil = cosUtil;
    }

    @PostMapping ("/edit")
    public Map<String, String> editUserInfo(@RequestParam(value = "file",required = false) MultipartFile file,
                                               @RequestParam(value = "newnickname") String newNickname,
                                               @RequestParam("account") String account) throws Exception {
        System.out.println(account);
        Map<String, String> response = new HashMap<>();


        // 执行更新操作
        if (file != null && !file.isEmpty()) {
            String imageUrl = cosUtil.uploadFile(file);
            UpdateWrapper<User> avatarUpdateWrapper = new UpdateWrapper<>();
            avatarUpdateWrapper.set("avatar", imageUrl).eq("account", account);
            userService.update(avatarUpdateWrapper);
            response.put("avatarUrl", imageUrl);
        }
        UpdateWrapper<User> nicknameUpdateWrapper = new UpdateWrapper<>();
        nicknameUpdateWrapper.set("nickname", newNickname).eq("account", account);
        userService.update(nicknameUpdateWrapper);
        response.put("nickname", newNickname);
        return response;
    }

    @GetMapping ("/edit1")
    public Map<String, String> editUserInfo(
                                            @RequestParam(value = "newnickname") String newNickname,
                                            @RequestParam("account") String account) throws Exception {
        Map<String, String> response = new HashMap<>();
        UpdateWrapper<User> nicknameUpdateWrapper = new UpdateWrapper<>();
        nicknameUpdateWrapper.set("nickname", newNickname).eq("account", account);
        userService.update(nicknameUpdateWrapper);
        response.put("nickname", newNickname);
        return response;
    }


}
