package com.jake.user.ServiceImpl;

import com.jake.user.Dao.UserSmsCode;
import com.jake.user.Dao.UserSmsCodeDao;
import com.jake.user.Service.UserService;
import com.jake.user.vo.GetSmsCodeReqVo;
import com.jake.user.vo.LoginByMobileReqVo;
import com.jake.user.vo.LoginByMobileResVo;
import com.jake.user.vo.LoginExitReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    UserSmsCodeDao userSmsCodeDao;
    @Override
    public boolean getSmsCode(GetSmsCodeReqVo vo) {
        //隨機生成6位簡訊驗證碼
        String smsCode = String.valueOf((int) (Math.random()*100000+1));
        //真實場景 需要呼叫簡訊平台介面
        //儲存使用者簡訊驗證碼資訊至簡訊驗證碼資訊表
        UserSmsCode userSmsCode = UserSmsCode.builder()
                .mobileNo(vo.getMobileNo())
                .smsCode(smsCode)
                .sendTime(new Timestamp(new Date().getTime()))
                .createTime(new Timestamp(new Date().getTime())).build();
        userSmsCodeDao.insert(userSmsCode);
        return true;
    }

    @Override
    public LoginByMobileResVo loginByMobile(LoginByMobileReqVo vo) {
        return null;
    }

    @Override
    public boolean loginExit(LoginExitReqVo vo) {
        return false;
    }


}
