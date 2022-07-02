package com.jake.user.Dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao {
    int insert(UserInfo userInfo);
    UserInfo selectByMobileNo(String mobileNo);
    int updateById(UserInfo userInfo);
}
