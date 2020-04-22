package com.wyyuand.service;

import com.wyyuand.domain.CircleComment;
import com.wyyuand.utils.PageBean;

public interface CircleCommentService {
    public PageBean<CircleComment> queryAllByPage(int pageNum, int pageSize);
    /*
后台
* */
    public PageBean<CircleComment> queryManyByPage(String keyword,int pageNum, int pageSize);

    public boolean delete(String id );

}
