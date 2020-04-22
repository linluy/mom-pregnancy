package com.wyyuand.dao;


import com.wyyuand.domain.CircleReply;

import java.util.List;

public interface CircleReplyMapperExt {

    public   List<CircleReply>  findByPage(int start, int size);   //分页查找
    public  int  queryAllCount();


    public  int  queryAllByKeyCount(String keyword);
    public   List<CircleReply>  queryManyByPage(String keyword,int start,int size);   //分页查找

    public  int  deleteAll(String[] ids);
    public  int  deleteAllByCommentId(String[] ids);
    public  int  deleteByCommentId(String id);


}