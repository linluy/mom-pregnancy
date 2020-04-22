package com.wyyuand.service;

import com.wyyuand.dao.PreTimeMapper;
import com.wyyuand.dao.PreTimeMapperExt;
import com.wyyuand.domain.PreTime;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PreTimeServiceImpl  implements  PreTimeService{

    @Autowired
    PreTimeMapper timeTabMapper;

    @Autowired
    PreTimeMapperExt timeTabMapperExt;
    @Override
    public List<PreTime> queryAll() {
        return timeTabMapperExt.queryAllASC();
    }

    @Override
    public PreTime queryById(String id) {
        return timeTabMapper.selectByPrimaryKey(id);
    }


    @Override
    public boolean update(PreTime timeTab) {
//        timeTab.setCheckId(StringUtil.pid());
        timeTab.setStatus("0");
        timeTab.setCheckDate(new Date());

        int count = timeTabMapper.updateByPrimaryKey(timeTab);

        System.out.println("count---->"+count);
        return count>0;
    }

    @Override
    public PageBean<PreTime> queryAllByPage(int pageNum, int pageSize) {

        PageBean<PreTime> pageBean = new PageBean<PreTime>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = timeTabMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<PreTime> lists = timeTabMapperExt.findByPage(start,size);

        for (PreTime list : lists) {
            System.out.println("type---------->"+list.getCheckPre());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public PageBean<PreTime> queryManyByPage(String keyword, int pageNum, int pageSize) {

        PageBean<PreTime> pageBean = new PageBean<PreTime>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = timeTabMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<PreTime> lists = timeTabMapperExt.queryManyByPage(keyword,start,size);

        for (PreTime list : lists) {
            System.out.println("type---------->"+list.getCheckId());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }
}
