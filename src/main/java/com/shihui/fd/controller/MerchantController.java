package com.shihui.fd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.shihui.common.DTO.SubmitAuditRequest;
import com.shihui.fd.entity.Merchant;
import com.shihui.fd.mapper.MerchantMapper;
import com.shihui.fd.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private MerchantMapper merchantMapper;

    @PostMapping("/submitAudit")
    public ResponseEntity<String> submitAudit(@RequestBody SubmitAuditRequest request) {
        // 获取前端传递过来的表单数据
        String code = request.getCode();
        String merchantName = request.getMerchantName();
        String phone = request.getPhone();
        String storeName = request.getStoreName();
        String storeLocation = request.getStoreLocation();

        // 向微信服务器请求获取用户 openid
        String openid = getOpenidFromWeixin(code);
        QueryWrapper<Merchant>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",openid);

        // 查询商家信息
        Merchant merchant = merchantMapper.selectOne(queryWrapper);

        if (merchant == null) {
            // 如果商家信息不存在，则插入新的记录
            merchant = new Merchant();
            merchant.setAccount(openid);
            merchant.setName(merchantName);
            merchant.setPhone(phone);
            merchant.setAuditStoreName(storeName);
            merchant.setAuditStoreLocation(storeLocation);
            merchant.setAuditStatus(0); // 设置默认审核状态为0
            merchantService.save(merchant);
            return ResponseEntity.ok("提交审核成功");
        } else {
            // 商家信息已存在，返回错误信息
            return ResponseEntity.badRequest().body("当前用户已提交审核，请勿重复提交");
        }
    }

    // 向微信服务器请求获取用户 openid 的方法
    private String getOpenidFromWeixin(String code) {
        // 发送请求到微信服务器获取 openid
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", "wxf070c908dffe673d");
        data.put("secret", "b095a8b9567ad9de36b4e11da040406b");
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");


        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        String openid = obj.getString("openid");
        return openid;
    }
}
