package com.wyyuand.service;


import com.wyyuand.dao.KnowledgeMapper;
import com.wyyuand.dao.KnowledgeMapperExt;
import com.wyyuand.domain.Knowledge;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


//实现Serializable接口

@Service
public class KnowledgeServiceImpl implements KnowledgeService, Serializable {


    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Autowired
    KnowledgeMapperExt knowledgeMapperExt;

    @Override
    public List<Knowledge> selectKnowledge() {
//        List<Knowledge> knowledgeList = knowledgeMapper.selectByExample(null);
        List<Knowledge> knowledgeList = knowledgeMapperExt.queryExceptDate();
        return knowledgeList;
    }
    @Override
    public PageBean<Knowledge> queryAllByPage(int pageNum, int pageSize) {
//        <Knowledge> knowledgeList = knowledgeMapper.selectByExample(null);

        PageBean<Knowledge> pageBean = new PageBean<Knowledge>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = knowledgeMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Knowledge> lists = knowledgeMapperExt.findByPage(start,size);

        for (Knowledge list : lists) {
            System.out.println("type---------->"+list.getTitle());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }
    @Override
    public boolean delete(String id) {
//        int count = knowledgeMapper.deleteByPrimaryKey(id);
        Knowledge knowledge = knowledgeMapper.selectByPrimaryKey(id);
        knowledge.setStatus(1);
        int count = knowledgeMapper.updateByPrimaryKey(knowledge);
        return count>0;
    }
    @Override
    public boolean deleteAll(String[] myIds) {
        int count = 0;
        try {
            count = knowledgeMapperExt.deleteAll(myIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count>0;
    }
    @Override
    public Knowledge queryById(String id) {
        Knowledge knowledge = knowledgeMapper.selectByPrimaryKey(id);
        return knowledge;
    }
    @Override
    public boolean save(Knowledge knowledge,String org ) {

        knowledge.setId(StringUtil.createRandomNum(7));
        knowledge.setPublishdate(new Date());
        knowledge.setStatus(0);
        knowledge.setImages(org);
        int count =  knowledgeMapper.insert(knowledge);
        return count>0;
    }
//    String org
    @Override
    public boolean update(Knowledge knowledge,String org) {

        knowledge.setPublishdate(new Date());
        knowledge.setStatus(0);
        knowledge.setImages(org);
        int count = knowledgeMapper.updateByPrimaryKey(knowledge);
        return count>0;
    }
    @Override
    public PageBean<Knowledge> queryManyByPage(String keyword, int pageNum, int pageSize) {

        PageBean<Knowledge> pageBean = new PageBean<Knowledge>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = knowledgeMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Knowledge> lists = knowledgeMapperExt.queryManyByPage(keyword,start,size);

        for (Knowledge list : lists) {
            System.out.println("type---------->"+list.getTitle());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;

    }

}
