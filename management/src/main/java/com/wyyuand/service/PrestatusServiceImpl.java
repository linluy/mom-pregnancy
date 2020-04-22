package com.wyyuand.service;


import com.wyyuand.dao.PrestatusMapper;
import com.wyyuand.dao.PrestatusMapperExt;
import com.wyyuand.domain.Prestatus;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrestatusServiceImpl implements PrestatusService {


    @Autowired
    PrestatusMapperExt prestatusMapperExt;

    @Autowired
    PrestatusMapper prestatusMapper;

    @Override
    public Prestatus queryByPeriod(String period) {
        return prestatusMapperExt.queryByPeriod(period);
    }
    @Override
    public Prestatus queryById(String id) {
        return prestatusMapper.selectByPrimaryKey(id);
    }
    @Override
    public PageBean<Prestatus> queryAllByPage(int pageNum, int pageSize) {
        PageBean<Prestatus> pageBean = new PageBean<Prestatus>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = prestatusMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Prestatus> lists = prestatusMapperExt.findByPage(start,size);

//        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }
    @Override
    public boolean update(Prestatus prestatus, String org) {

        prestatus.setPicture(org);
        int count = prestatusMapper.updateByPrimaryKey(prestatus);
        return count>0;

    }
    @Override
    public PageBean<Prestatus> queryManyByPage(String keyword, int pageNum, int pageSize) {

        PageBean<Prestatus> pageBean = new PageBean<Prestatus>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = prestatusMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Prestatus> lists = prestatusMapperExt.queryManyByPage(keyword,start,size);

        for (Prestatus list : lists) {
            System.out.println("type---------->"+list.getMomSay());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }
}
