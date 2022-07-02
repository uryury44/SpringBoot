package com.jake.user.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginByMobileReqVo {

    private String reqId;
    private String mobileNo;
    private String smsCode;
}
