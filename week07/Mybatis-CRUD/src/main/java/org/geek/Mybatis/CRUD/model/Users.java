package org.geek.Mybatis.CRUD.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Users {
    int uid;
    String name;
    String gender;
    String number;
    String address;
    Timestamp create_time;
    Timestamp last_update_time;
}
