package com.wyyuand.dao;

import com.wyyuand.domain.BabyWeight;

import java.util.List;

public interface BabyWeightMapperExt {

    public List<BabyWeight> queryCulWeight(String userId);

    public BabyWeight queryBabyWeight(String userId);

    public  int  queryAllCount();
    public   List<BabyWeight>  findByPage(int start,int size);   //分页查找
    public  int  deleteAll(String[] ids);


    public  int  queryAllByKeyCount(String keyword);
    public   List<BabyWeight>  queryManyByPage(String keyword,int start,int size);   //分页查找



}
