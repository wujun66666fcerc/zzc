package com.shihui.fd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.fd.entity.BrowseHistory;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.mapper.BrowseHistoryMapper;
import com.shihui.fd.service.IBrowseHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Service
public class BrowseHistoryServiceImpl extends ServiceImpl<BrowseHistoryMapper, BrowseHistory> implements IBrowseHistoryService {
    @Autowired
    BrowseHistoryMapper browseHistoryMapper;


    @Override
    public boolean addOrUpdateBrowserHistory(BrowseHistory browserHistory) {
        // 构建查询条件，根据 account 和 dish_id 查询是否存在记录
        QueryWrapper<BrowseHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", browserHistory.getAccount())
                .eq("dish_id", browserHistory.getDishId());
        BrowseHistory existing = browseHistoryMapper.selectOne(queryWrapper);

        if (existing != null) {
            // 如果已存在，则更新记录的浏览时间
            existing.setBrowseTime(browserHistory.getBrowseTime());
            int rows = browseHistoryMapper.updateBrowseTimeByAccountAndDishId(existing);
            return rows > 0;
        } else {
            // 如果不存在，则插入新记录
            int rows = browseHistoryMapper.insert(browserHistory);
            return rows > 0;
        }
    }

    @Override
    public List<Dish> getBrowseHistoryByAccount(String account, Integer offset, Integer pageSize) {
        return browseHistoryMapper.getBrowseHistoryByAccount(account,offset,pageSize);
    }
}
