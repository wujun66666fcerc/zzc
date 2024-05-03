package com.shihui.fd.service;

import com.shihui.fd.entity.Merchant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shihui.fd.entity.MerchantStoreInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface IMerchantService extends IService<Merchant> {

    MerchantStoreInfo getMerchantStoreInfo(String account);


}
