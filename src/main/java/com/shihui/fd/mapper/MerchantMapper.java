package com.shihui.fd.mapper;

import com.shihui.fd.entity.Merchant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shihui.fd.entity.MerchantStoreInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface MerchantMapper extends BaseMapper<Merchant> {

    MerchantStoreInfo getMerchantStoreInfo(@Param("merchantAccount") String merchantAccount);
}

