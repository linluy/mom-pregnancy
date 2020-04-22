package com.wyyuand.dao;


import com.wyyuand.domain.Circle;
import com.wyyuand.domain.CircleComment;

import java.util.List;

public interface CircleCommentMapperExt {

    public   List<CircleComment>  findByPage(int start, int size);   //分页查找
    public  int  queryAllCount();

    public  int  queryAllByKeyCount(String keyword);
    public   List<CircleComment>  queryManyByPage(String keyword,int start,int size);   //分页查找

    public List<CircleComment> selectCommentId(String id);
    public int  deleteByCircleId(String id);
}