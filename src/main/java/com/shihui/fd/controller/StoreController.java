package com.shihui.fd.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.common.DTO.StoreRequest;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.Ownership;
import com.shihui.fd.entity.Store;
import com.shihui.fd.mapper.OwnershipMapper;
import com.shihui.fd.mapper.StoreMapper;
import com.shihui.fd.service.IDishService;
import com.shihui.fd.service.IOwnershipService;
import com.shihui.fd.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private IStoreService storeService;

    @Autowired
    private IOwnershipService ownershipService;

    @Autowired
    private OwnershipMapper ownershipMapper;

    @Autowired
    private IDishService dishService;

    /**
     * 新增或修改
     * 如果传入的storeid为空则新增，否则修改
     * @param storeRequest
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdateStore(@RequestBody StoreRequest storeRequest)
    {
        String merchantId = storeRequest.getMerchantId();
        Store store = storeRequest.getStore();
        //先标记是否是新增
        boolean add;
        if (store.getStoreId() == null)
            add = true;
        else
            add = false;
        //新增或修改一个店铺
        boolean flag = storeService.saveOrUpdate(store);
        if (!flag)
            return Result.fail("fail");

        //如果新增一个店铺，那么要新增关系表，增加店铺和商家的关系
        if(add)
        {
            Ownership ow = new Ownership();
            ow.setMerchantAccount(merchantId);
            ow.setStoreId(store.getStoreId());
            ow.setMerchantAccount(merchantId);
            ownershipService.save(ow);
        }
        //如果修改了店铺信息，那么店铺所有的菜品的位置肯会更改
        //修改菜品的位置
        else
        {
            QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
            dishQueryWrapper.eq("store_id", store.getStoreId());
            //根据店铺id = store_id查找店铺的菜品
            List<Dish> dishList = dishService.list(dishQueryWrapper);
            //对每个菜品修改detailed_location 和 location
            for (Dish d : dishList){
                d.setDetailedLocation(store.getStoreLocation());
                d.setLocation(store.getLocation());
                dishService.updateById(d);
            }
        }
        return Result.success("success");
    }

    /**
     * 删除店铺
     * @param storeRequest
     * @return
     */
    @PostMapping ("/deleteById")
    public Result<String> deleteById(@RequestBody StoreRequest storeRequest)
    {
        String merchantId = storeRequest.getMerchantId();
        Store store = storeRequest.getStore();
        //删除店铺首先要删除店铺的所有菜品
        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        dishQueryWrapper.eq("store_id", store.getStoreId());
        //批量删除
        if(!dishService.remove(dishQueryWrapper))
            return Result.fail("fail");
        //还要删除相关的关系
        QueryWrapper<Ownership> ownershipQueryWrapper = new QueryWrapper<>();
        ownershipQueryWrapper.eq("store_id", store.getStoreId()).eq("merchant_account", merchantId);
        if(!ownershipService.remove(ownershipQueryWrapper))
            return Result.fail("fail");
        //再删除店铺
        if(!storeService.removeById(store.getStoreId()))
            return Result.fail("fail");
        return Result.success("success");
    }

}
