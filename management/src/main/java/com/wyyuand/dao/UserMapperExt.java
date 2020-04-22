package com.wyyuand.dao;


import com.wyyuand.domain.User;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface UserMapperExt {

    public User queryByEmail(User user);
    public int updateByEmail(User user) throws Exception;
    public Integer queryEmailUser(User user);

    public int  updateUser(User user);


    public  int  queryAllCount();
    public List<User> findByPage(int start, int size);   //分页查找



    public  int  queryAllByKeyCount(String keyword);
    public   List<User>  queryManyByPage(String keyword,int start,int size);   //分页查找


}