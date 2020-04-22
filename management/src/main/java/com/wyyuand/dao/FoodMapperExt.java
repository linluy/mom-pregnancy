package com.wyyuand.dao;

import com.wyyuand.domain.Food;

import java.util.List;
import java.util.Map;

public interface FoodMapperExt {
//    public int deleteAll(List list) throws Exception;            //删除，，将状态改为1。。。。。
    public int deleteAll(String[] myIds) throws Exception;            //删除，，将状态改为1。。。。。

    public List<Food> findByPage(int start, int size, String type);
    public int queryAllCount(String type);
    public List<Food> queryAllByType(String type);
    public List<Food> queryAllByWeekday(String weekday);


    public  int  queryAllByKeyCount(String keyword,String type);
    public   List<Food>  queryManyByPage(String keyword,int start,int size,String type);   //分页查找
}