package com.wyyuand.dao;

import com.wyyuand.domain.Food;
import com.wyyuand.domain.Knowledge;

import java.util.List;

public interface KnowledgeMapperExt {
    public  List<Knowledge> queryExceptDate();
    public  int  deleteAll(String[] ids);
    public  int  queryAllCount();
    public   List<Knowledge>  findByPage(int start,int size);   //分页查找

    public  int  queryAllByKeyCount(String keyword);
    public   List<Knowledge>  queryManyByPage(String keyword,int start,int size);   //分页查找

}