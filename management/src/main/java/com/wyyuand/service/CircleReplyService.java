package com.wyyuand.service;

import com.wyyuand.domain.CircleReply;
import com.wyyuand.utils.PageBean;

public interface CircleReplyService {

    PageBean<CircleReply> queryAllByPage(int pageNum, int pageSize);
    public PageBean<CircleReply> queryManyByPage(String keyword,int pageNum, int pageSize);
    public  boolean delete(String id);

    public boolean deleteAll(String[] myIds);

}
