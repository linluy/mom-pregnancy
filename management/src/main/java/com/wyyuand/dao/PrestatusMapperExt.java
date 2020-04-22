package com.wyyuand.dao;

import com.wyyuand.domain.Prestatus;
import com.wyyuand.utils.PageBean;

import java.util.List;


public interface PrestatusMapperExt {

//    用户

    public Prestatus queryByPeriod(String period);

//    管理员
    public  int  queryAllCount();
    public List<Prestatus> findByPage(int start, int size);   //分页查找

//    模糊查询
    public  int  queryAllByKeyCount(String keyword);
    public   List<Prestatus>  queryManyByPage(String keyword,int start,int size);   //分页查找



}