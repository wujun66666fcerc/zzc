package com.shihui.fd.service.impl;

import com.shihui.fd.entity.Merchant;
import com.shihui.fd.entity.MerchantStoreInfo;
import com.shihui.fd.mapper.MerchantMapper;
import com.shihui.fd.service.IMerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {
    @Autowired MerchantMapper merchantMapper;

    @Override
    public MerchantStoreInfo getMerchantStoreInfo(String merchantAccount) {
        return merchantMapper.getMerchantStoreInfo(merchantAccount);
    }
}
