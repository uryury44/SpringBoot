package com.jake.user.Service;

import com.jake.user.Expection.BizException;
import com.jake.user.vo.GetSmsCodeReqVo;
import com.jake.user.vo.LoginByMobileReqVo;
import com.jake.user.vo.LoginByMobileResVo;
import com.jake.user.vo.LoginExitReqVo;

public interface UserService {

    public boolean getSmsCode(GetSmsCodeReqVo vo);

    public LoginByMobileResVo loginByMobile(LoginByMobileReqVo vo) throws BizException;

    public boolean loginExit(LoginExitReqVo vo);


}
