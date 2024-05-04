package com.shihui;

import com.shihui.common.DTO.StoreRequest;
import com.shihui.fd.controller.StoreController;
import com.shihui.fd.entity.Store;
import com.shihui.fd.entity.User;
import com.shihui.fd.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ZzctestApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    void testMapper() {
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Autowired
    StoreController storeController;
    @Test
    void testadd()
    {
        Store store = new Store();
        store.setStoreId(890347522);
        store.setStoreName("wi");
        store.setStoreLocation("匆匆那年");
        store.setLocation("荷园");
        StoreRequest storeRequest = new StoreRequest("ol8166_fY1qsI1i3-NDtD89Z5JRQ", store);
        storeController.saveOrUpdateStore(storeRequest);
    }

}
