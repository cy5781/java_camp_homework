package org.geek.Mybatis.CRUD.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Orders {
    int id;
    int goodsid;
    int uid;
    String status;
    String ordernumber;
    String orderdetail;
    Timestamp create_time;
    Timestamp last_update_time;
}
