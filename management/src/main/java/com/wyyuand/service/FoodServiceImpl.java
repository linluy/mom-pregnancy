package com.wyyuand.service;


import com.wyyuand.dao.FoodMapper;
import com.wyyuand.dao.FoodMapperExt;
import com.wyyuand.domain.Food;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService, Serializable {

    @Autowired
    FoodMapper foodMapper;
    @Autowired
    FoodMapperExt foodExt;


    @Override
    public boolean delete(String id) {


        Food food  = foodMapper.selectByPrimaryKey(id);
        food.setStatus("1");
        int count = foodMapper.updateByPrimaryKey(food);
        return  count>0;

    }

    @Override
    public boolean deleteAll(String[]  myIds) {

//        Food food  = foodMapper.selectByPrimaryKey(ids);
//        food.setStatus("1");
        int count = 0;
        try {
           count = foodExt.deleteAll(myIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count>0;

    }


    @Override
    public boolean save(Food food, String org) {


        food.setPicture(org);
        food.setId(StringUtil.pid());
        food.setStatus("0");
        int count = foodMapper.insert(food);
        return count>0;

    }

    @Override
    public boolean update(Food food, String org) {

        food.setPicture(org);
        food.setStatus("0");
        food.setUpdatetime(new Date());
        int count = foodMapper.updateByPrimaryKey(food);
        System.out.println(count);
        return count>0;
    }
//
//
    @Override
    public Food queryById(String id) {
        Food food = foodMapper.selectByPrimaryKey(id);
        return food;
    }

    @Override
    public PageBean<Food> queryAllByPage(int pageNum, int pageSize,String type) {

        PageBean<Food> pageBean = new PageBean<Food>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = foodExt.queryAllCount(type);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Food> lists = foodExt.findByPage(start,size,type);

        for (Food list : lists) {
            System.out.println("type---------->"+list.getType());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);

        return pageBean;
    }

    @Override
    public int queryAllCount(String type) {
        int count = foodExt.queryAllCount(type);
        System.out.println("count---->"+count);
        return count;
    }

    @Override
    public List<Food> queryAll(String type) {

        List<Food> list = foodExt.queryAllByType(type);
        for (Food food : list) {
            System.out.println(food.getBfpJ());
            System.out.println(food.getBabyJ());
            System.out.println(food.getPwJ());
        }
        return list;
    }

    @Override
    public List<Food> queryAllByWeekday(String weekday) {

        List<Food> list = foodExt.queryAllByWeekday(weekday);

        for (Food food : list) {
            System.out.println(food.getBfpJ());
            System.out.println(food.getBabyJ());
            System.out.println(food.getPwJ());
        }
        return list;
    }

    @Override
    public PageBean<Food> queryManyByPage(String keyword, int pageNum, int pageSize, String type) {


        PageBean<Food> pageBean = new PageBean<Food>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = foodExt.queryAllByKeyCount(keyword,type);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Food> lists = foodExt.queryManyByPage(keyword,start,size,type);

        for (Food list : lists) {
            System.out.println("type---------->"+list.getType());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);

        return pageBean;

    }


}
