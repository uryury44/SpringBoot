package com.jake.user.Controller;


import com.jake.user.Expection.BizException;
import com.jake.user.ServiceImpl.UserServiceImpl;
import com.jake.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping(value = "getSmsCode",method = RequestMethod.POST)
    public Boolean getSmsCode(@RequestParam("reqId") String reqId,
                              @RequestParam("mobileNo") String mobileNo){
        GetSmsCodeReqVo vo = GetSmsCodeReqVo.builder().reqId(reqId).mobileNo(mobileNo).build();
        boolean result = userServiceImpl.getSmsCode(vo);
        return result;
    }
    @RequestMapping(value = "loginByMobile",method = RequestMethod.POST)
    public ApiResponse LoginByMobile(@RequestParam("reqId") String reqId,
                                     @RequestParam("mobileNo") String mobileNo,
                                     @RequestParam("smsCode") String smsCode) throws BizException {
        LoginByMobileReqVo vo = LoginByMobileReqVo.builder().reqId(reqId).mobileNo(mobileNo).smsCode(smsCode).build();
        LoginByMobileResVo resVo = userServiceImpl.loginByMobile(vo);

        return ApiResponse.success("200","成功",resVo);
    }
    @RequestMapping(value = "loginExit",method = RequestMethod.POST)
    public Boolean loginExit(@RequestParam("userId") String userId,
                                     @RequestParam("accessToken") String accessToken
                                    ){
        LoginExitReqVo vo = LoginExitReqVo.builder().userId(userId).accessToken(accessToken).build();
        boolean result = userServiceImpl.loginExit(vo);
        return result;
    }





}
