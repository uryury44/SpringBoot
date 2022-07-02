package com.jake.user.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserSmsCodeDao {
    int insert(UserSmsCode userSmsCode);
    UserSmsCode selectByMobileNo(String mobileNo);
}
