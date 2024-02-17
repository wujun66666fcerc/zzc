package com.shihui.fd.controller;

import com.shihui.common.Utils.CosUtil;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.User;
import com.shihui.fd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/edit")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不能为空");
        }

        // 上传文件
        String url = cosUtil.uploadFile(file);

        // 构建包含 URL 的 JSON 对象
        String responseJson = "{\"url\": \"" + url + "\"}";

        return ResponseEntity.ok().body(responseJson);
    }


}
