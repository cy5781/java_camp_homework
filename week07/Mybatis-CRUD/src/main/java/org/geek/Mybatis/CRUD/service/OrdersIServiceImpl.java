package org.geek.Mybatis.CRUD.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geek.Mybatis.CRUD.mapper.GoodsMapper;
import org.geek.Mybatis.CRUD.mapper.OrdersMapper;
import org.geek.Mybatis.CRUD.model.Goods;
import org.geek.Mybatis.CRUD.model.Orders;
import org.geek.Mybatis.CRUD.model.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrdersIServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements ordersIService {

    @Resource
    private OrdersMapper ordersMapper;

    //写主库
    @DS("master")
    @Override
    public void saveOrders() {
        Orders order = new Orders();
        order.setCreate_time(new Timestamp(new Date().getTime()));
        order.setLast_update_time(new Timestamp(new Date().getTime()));
        order.setOrderdetail("商品详情");
        order.setGoodsid(1);
        order.setOrdernumber(String.valueOf(new Random().nextInt(1000000)));
        order.setUid(1);
        order.setStatus("正常");
        ordersMapper.insert(order);
    }

    //主库查询
    @DS("slave")
    @Override
    public List<Orders> selectOrders() {
        return ordersMapper.selectList(new LambdaQueryChainWrapper<>(ordersMapper)
                .eq(Orders::getOrdernumber,123456));
    }

}
