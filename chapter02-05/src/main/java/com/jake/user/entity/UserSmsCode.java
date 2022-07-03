package com.jake.user.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserSmsCode {
    private String id;
    private String mobileNo;
    private String smsCode;
    private Timestamp sendTime;
    private Timestamp createTime;

}
