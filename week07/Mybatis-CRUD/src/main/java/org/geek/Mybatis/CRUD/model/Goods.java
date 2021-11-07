package org.geek.Mybatis.CRUD.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Goods {
    int goodsId;
    String goodsname;
    String price;
    String category;
    Timestamp create_time;
    Timestamp last_update_time;
}
