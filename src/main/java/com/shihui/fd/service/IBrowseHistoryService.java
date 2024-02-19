package com.shihui.fd.service;

import com.shihui.fd.entity.BrowseHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shihui.fd.entity.Dish;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
public interface IBrowseHistoryService extends IService<BrowseHistory> {

    boolean addOrUpdateBrowserHistory(BrowseHistory browserHistory);

    List<Dish> getBrowseHistoryByAccount(String account, Integer offset, Integer pageSize);
}
