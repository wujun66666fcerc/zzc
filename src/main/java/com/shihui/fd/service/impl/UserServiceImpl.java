package com.shihui.fd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.shihui.fd.entity.User;
import com.shihui.fd.mapper.UserMapper;
import com.shihui.fd.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<String> codetoopenid(String code) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", "wxf070c908dffe673d");
        data.put("secret", "b095a8b9567ad9de36b4e11da040406b");
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");


        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        String openid = obj.getString("openid");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", openid);
        User user = baseMapper.selectOne(queryWrapper);
        //System.out.println(user);
        Map<String, Object> userMap = new HashMap<>();
        if (user != null) {
            userMap.put("account",user.getAccount());
            userMap.put("nickname",user.getNickname());
            userMap.put("avatar",user.getAvatar());

            // 用户已存在，返回用户信息
            //return ResponseEntity.status(HttpStatus.OK).body(user.toString());
        } else {
            // 用户不存在，创建一个新的用户对象，并插入到用户表中

            User newUser = new User();
            newUser.setAccount(openid); // 设置 openid
            // 设置默认昵称为 "zzc_" + 随机字符串
            newUser.setNickname("zzc_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0,8));
            // avatar 字段为空
            // 插入新用户记录
            userMapper.insert(newUser);
            userMap.put("account", newUser.getAccount());
            userMap.put("nickname", newUser.getNickname());
            userMap.put("avatar",newUser.getAvatar());
            // 返回新用户信息
            //return ResponseEntity.status(HttpStatus.OK).body(newUser.toString());
        }
        String jsonResponse = JSON.toJSONString(userMap);
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);


        //return ResponseEntity.status(HttpStatus.OK).body(obj.toJSONString());
    }


}
