package com.shihui.fd.service;

import com.qcloud.cos.COSClient;
import com.shihui.fd.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface IUserService extends IService<User> {
    public ResponseEntity<String> codetoopenid(String code,Integer isMerchant);


}
