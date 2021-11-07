package org.geek.Mybatis.CRUD.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geek.Mybatis.CRUD.mapper.GoodsMapper;
import org.geek.Mybatis.CRUD.model.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface goodsIService extends IService<Goods> {

    public List<Goods> selectGoods();
}
