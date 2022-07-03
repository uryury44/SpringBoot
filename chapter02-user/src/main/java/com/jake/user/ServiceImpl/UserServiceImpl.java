package com.jake.user.ServiceImpl;

import com.jake.user.entity.UserInfo;
import com.jake.user.Dao.UserInfoDao;
import com.jake.user.entity.UserSmsCode;
import com.jake.user.Dao.UserSmsCodeDao;
import com.jake.user.Expection.BizException;
import com.jake.user.Service.UserService;
import com.jake.user.vo.GetSmsCodeReqVo;
import com.jake.user.vo.LoginByMobileReqVo;
import com.jake.user.vo.LoginByMobileResVo;
import com.jake.user.vo.LoginExitReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserSmsCodeDao userSmsCodeDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean getSmsCode(GetSmsCodeReqVo vo) {
        //隨機生成6位簡訊驗證碼
        String smsCode = String.valueOf((int) (Math.random()*100000+1));
        //真實場景 需要呼叫簡訊平台介面
        //儲存使用者簡訊驗證碼資訊至簡訊驗證碼資訊表
        UserSmsCode userSmsCode = UserSmsCode.builder()
                .mobileNo(vo.getMobileNo())
                .smsCode(smsCode)
                .sendTime(new Timestamp (new Date().getTime()))
                .createTime(new Timestamp(new Date().getTime())).build();
        userSmsCodeDao.insert(userSmsCode);
        return true;
    }

    @Override
    public LoginByMobileResVo loginByMobile(LoginByMobileReqVo loginByMobileReqVo) throws BizException {
        UserSmsCode userSmsCode = userSmsCodeDao.selectByMobileNo(loginByMobileReqVo.getMobileNo());
        if(userSmsCode == null){
            throw new BizException(-1,"驗證碼輸入錯誤");
        }else if (!userSmsCode.getSmsCode().equals(loginByMobileReqVo.getSmsCode())){
            throw new BizException(-1,"驗證碼輸入錯誤");
        }else{
            UserInfo userInfo = userInfoDao.selectByMobileNo(loginByMobileReqVo.getMobileNo());
            //註冊
            if (userInfo == null){
                String userId = String.valueOf((int) Math.random() * 100000+1);
                userInfo = UserInfo.builder().userId(userId).mobileNo(loginByMobileReqVo.getMobileNo()).isLogin("1").loginTime(new Timestamp(new Date().getTime())).build();
                userInfoDao.insert(userInfo);
            //登入
            }else{
                userInfo = UserInfo.builder().id(userInfo.getId()).isLogin("1").loginTime(new Timestamp(new Date().getTime())).build();
                userInfoDao.updateById(userInfo);
            }
            //將使用者階段資訊儲存至Redis服務
            String accessToken = UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
            redisTemplate.opsForValue().set("accessToken",userInfo,30, TimeUnit.DAYS);
            LoginByMobileResVo loginByMobileResVo = LoginByMobileResVo.builder()
                    .userId(userInfo.getUserId()).accessToken(accessToken).build();
            return loginByMobileResVo;
        }
    }

    @Override
    public boolean loginExit(LoginExitReqVo loginExitReqVo) {
        try {
            redisTemplate.delete(loginExitReqVo.getAccessToken());
            return true;
        }catch (Exception e){
            log.error(e.toString() + "_" + e);
            return false;
        }
    }
}
