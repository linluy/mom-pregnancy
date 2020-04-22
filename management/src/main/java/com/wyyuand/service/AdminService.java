package com.wyyuand.service;

import com.wyyuand.domain.Admin;
import org.springframework.ui.Model;

import java.util.List;

public interface AdminService {
    public Admin queryById(String id) throws Exception;
    public List<Admin> queryAll() throws Exception;    //查询全部管理员
    public boolean save(Admin admin) throws Exception;   //添加管理员
    public boolean delete(String id) throws Exception;    //根据id删除管理员
    public boolean update(Admin admin) throws Exception;   //更新管理员

    public boolean updatePassword(Admin admin) throws Exception;

    public Admin queryByEmail(String admEmail,String password) ;


}
