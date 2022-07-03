package com.jake.user.Dao;

import com.jake.user.entity.UserSmsCode;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserSmsCodeDao {
    int insert(UserSmsCode userSmsCode);
    UserSmsCode selectByMobileNo(String mobileNo);
}
