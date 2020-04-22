package com.wyyuand.service;

import com.wyyuand.domain.Knowledge;
import com.wyyuand.domain.PreTime;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface PreTimeService {
    public List<PreTime> queryAll();
    public PreTime queryById(String id);
    public boolean update(PreTime timeTab);
    public PageBean<PreTime> queryAllByPage(int pageNum, int pageSize);

//    模糊查询
    public PageBean<PreTime> queryManyByPage(String keyword,int pageNum, int pageSize);

}
