package com.wyyuand.service;

import com.wyyuand.domain.Prestatus;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface PrestatusService {

    public Prestatus queryByPeriod(String period);
    public Prestatus queryById(String id);

    public PageBean<Prestatus> queryAllByPage(int pageNum, int pageSize);
    public boolean update(Prestatus prestatus,String org);
    public PageBean<Prestatus> queryManyByPage(String keyword, int pageNum, int pageSize); //模糊查找


}
