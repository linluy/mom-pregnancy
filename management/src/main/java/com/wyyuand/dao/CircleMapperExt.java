package com.wyyuand.dao;


import com.wyyuand.domain.Circle;
import com.wyyuand.domain.CircleComment;

import java.util.List;

public interface CircleMapperExt {
    //查询帖子信息
    public List<Circle> findUserWithCircle() throws Exception;

    public List<CircleComment> findCommentWithCircle(String circleId) throws Exception;
    public Integer findCommentCount(String circleId) throws Exception;

    public  int  queryAllCount();
    public   List<Circle>  findByPage(int start,int size);   //分页查找

//    public  int  queryAllCountComment();

    public  int  queryAllByKeyCount(String keyword);
    public   List<Circle>  queryManyByPage(String keyword,int start,int size);   //分页查找


}