package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.common.DTO.StoreRequest;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Ownership;
import com.shihui.fd.entity.Store;
import com.shihui.fd.mapper.OwnershipMapper;
import com.shihui.fd.mapper.StoreMapper;
import com.shihui.fd.service.IOwnershipService;
import com.shihui.fd.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Controller
@RequestMapping("store")
public class StoreController {
    @Autowired
    private IStoreService storeService;

    @Autowired
    private IOwnershipService ownershipService;

    @Autowired
    private OwnershipMapper ownershipMapper;

    /**
     * 新增或修改
     * 如果传入的storeid为空则新增，否则修改
     * @param store
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdateStore(@RequestBody StoreRequest storeRequest)
    {
        String merchantId = storeRequest.getMerchantId();
        Store store = storeRequest.getStore();
        //新增或修改
        boolean flag = storeService.saveOrUpdate(store);


        //新增或修改关系表
        //根据商家id和店铺id查找关系id
        QueryWrapper<Ownership> ownershipQueryWrapper = new QueryWrapper<>();
        ownershipQueryWrapper.eq("merchant_account",merchantId).eq("store_id",store.getStoreId());
        Ownership ownership = ownershipMapper.selectOne(ownershipQueryWrapper);
        Integer ownershipId = ownership.getOwnershipId();

        Ownership ow = new Ownership(ownershipId, storeRequest.getMerchantId(), store.getStoreId());
        ownershipService.saveOrUpdate(ow);

        //修改菜品的位置

    }


}
