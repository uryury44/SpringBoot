package com.jake.user.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
public class UserSmsCode  implements Serializable {

    private String id;
    private String mobileNo;
    private String smsCode;
    private Timestamp sendTime;
    private Timestamp createTime;

}
