package com.wyyuand.service;


import com.wyyuand.domain.Knowledge;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface KnowledgeService {

    public boolean save(Knowledge knowledge,String org );
    public List<Knowledge> selectKnowledge();

    public PageBean<Knowledge> queryAllByPage(int pageNum, int pageSize);
    public boolean delete(String id);

    public boolean deleteAll(String[] myIds);
    public boolean update(Knowledge knowledge,String org);
    public Knowledge queryById(String id);

//模糊查询
    public PageBean<Knowledge> queryManyByPage(String keyword,int pageNum, int pageSize);
}
