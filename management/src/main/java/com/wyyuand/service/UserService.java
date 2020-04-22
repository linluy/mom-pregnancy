package com.wyyuand.service;

import com.wyyuand.domain.User;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface UserService {
    public User queryById(String id) throws Exception;            //根据Id查询用户
    public List<User> queryAll() throws Exception;                 //查询所有的用户
    public boolean save(User user) throws Exception;             //添加用户

    public boolean delete(String id) throws Exception;           //根据id删除用户
    public boolean update(User user) throws Exception;          //更新用户
    public boolean updatePassword(User user) throws Exception;             //根据邮箱 --->用户密码更改

    public User queryByEmail(User user) throws Exception;       //根据邮箱查询用户-----登录
    public PageBean<User> queryAllByPage(int pageNum, int pageSize);

    public PageBean<User> queryManyByPage(String keyword, int pageNum, int pageSize);


}
