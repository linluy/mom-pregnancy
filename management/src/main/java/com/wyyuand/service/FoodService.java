package com.wyyuand.service;


import com.wyyuand.domain.Food;
import com.wyyuand.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface FoodService {
//
//    public List<Cereal> queryAll();
    public Food queryById(String id);
//
    public boolean delete(String id);
    public boolean deleteAll(String[] myIds );

    public boolean save(Food food, String org);
    public boolean update(Food food, String org);

    public int queryAllCount(String type);
    public PageBean<Food> queryAllByPage(int pageNum, int pageSize, String type);
    public List<Food> queryAll(String type);

    public List<Food> queryAllByWeekday(String weekday);
    public PageBean<Food> queryManyByPage(String keyword,int pageNum, int pageSize, String type);


}
