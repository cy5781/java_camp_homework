package org.geek.Mybatis.CRUD.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geek.Mybatis.CRUD.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ordersIService extends IService<Orders> {
    public void saveOrders();
    public List<Orders> selectOrders();
}
