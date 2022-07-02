package com.jake.user.Dao;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserSmsCode {
    private String mobileNo;
    private String smsCode;
    private Timestamp sendTime;
    private Timestamp createTime;

}
