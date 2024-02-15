package com.shihui.fd.service.impl;

import com.shihui.fd.entity.User;
import com.shihui.fd.mapper.UserMapper;
import com.shihui.fd.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
