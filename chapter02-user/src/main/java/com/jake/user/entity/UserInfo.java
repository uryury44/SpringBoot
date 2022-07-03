package com.jake.user.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
public class UserInfo  implements Serializable {
    private String id;
    private String mobileNo;
    private String nickName;
    private String userId;
    private String password;
    private String isLogin;
    private String isDel;
    private Timestamp loginTime;
    private Timestamp createTime;

}
