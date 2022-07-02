package com.jake.user.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserInfo {
    private String mobileNo;
    private String userId;
    private String isLogin;
    private Timestamp longTime;
    private Timestamp createTime;

}
