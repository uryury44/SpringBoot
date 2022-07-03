package com.jake.user.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Data
@Builder
public class UserSmsCode {

    private String mobileNo;
    private String smsCode;
    private Timestamp sendTime;
    private Timestamp createTime;

}
