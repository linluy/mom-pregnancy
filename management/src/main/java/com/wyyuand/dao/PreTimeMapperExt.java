package com.wyyuand.dao;

import com.wyyuand.domain.Food;
import com.wyyuand.domain.PreTime;

import java.util.List;

public interface PreTimeMapperExt {
    public List<PreTime> queryAllASC();
    public   List<PreTime>  findByPage(int start,int size);   //分页查找
    public  int  queryAllCount();


    public  int  queryAllByKeyCount(String keyword);
    public   List<PreTime>  queryManyByPage(String keyword,int start,int size);   //分页查找

}