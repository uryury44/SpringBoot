package com.jake.user.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetSmsCodeReqVo {

    private String reqId;
    private String mobileNo;
}
