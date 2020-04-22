package com.wyyuand.dao;


import com.wyyuand.domain.Admin;

public interface AdminMapperExt {
    //根据邮箱更新管理员信息
    public int updateByEmail(Admin admin) throws Exception;

    public Admin selectAdmin(String admEmail,String admPassword);

}