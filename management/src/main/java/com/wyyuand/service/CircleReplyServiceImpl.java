package com.wyyuand.service;


import com.wyyuand.dao.CircleReplyMapper;
import com.wyyuand.dao.CircleReplyMapperExt;
import com.wyyuand.domain.CircleReply;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleReplyServiceImpl implements CircleReplyService {


    @Autowired
    CircleReplyMapper circleReplyMapper;

    @Autowired
    CircleReplyMapperExt circleReplyMapperExt;

    @Override
    public PageBean<CircleReply> queryAllByPage(int pageNum, int pageSize) {

        PageBean<CircleReply> pageBean = new PageBean<CircleReply>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleReplyMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<CircleReply> lists = circleReplyMapperExt.findByPage(start,size);

        for (CircleReply list : lists) {
            System.out.println("type---------->"+list.getCommentId());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public PageBean<CircleReply> queryManyByPage(String keyword, int pageNum, int pageSize) {


        PageBean<CircleReply> pageBean = new PageBean<CircleReply>();
        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleReplyMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();

        //封装每页显示的数据
        List<CircleReply> lists = circleReplyMapperExt.queryManyByPage(keyword,start,size);

        pageBean.setLists(lists);
        System.out.println(lists.size());

        return pageBean;

    }

    @Override
    public boolean delete(String replyId) {

        int count = circleReplyMapper.deleteByPrimaryKey(replyId);
        return count>0;
    }


    @Override
    public boolean deleteAll(String[] myIds) {

        int count = 0;
        try {
            count = circleReplyMapperExt.deleteAll(myIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count>0;
    }
}
