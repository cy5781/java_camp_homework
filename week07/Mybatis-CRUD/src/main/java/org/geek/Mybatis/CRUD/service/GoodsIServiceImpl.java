package org.geek.Mybatis.CRUD.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geek.Mybatis.CRUD.mapper.GoodsMapper;
import org.geek.Mybatis.CRUD.model.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsIServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements goodsIService {

    @Resource
    private GoodsMapper goodsMapper;

    //主库查询
    @DS("master")
    @Override
    public List<Goods> selectGoods() {
        return null;
    }
}
