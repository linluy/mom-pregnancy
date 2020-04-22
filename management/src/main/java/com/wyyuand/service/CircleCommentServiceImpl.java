package com.wyyuand.service;

import com.wyyuand.dao.CircleCommentMapper;
import com.wyyuand.dao.CircleCommentMapperExt;
import com.wyyuand.dao.CircleReplyMapperExt;
import com.wyyuand.domain.CircleComment;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CircleCommentServiceImpl implements CircleCommentService {

    @Autowired
    CircleCommentMapper circleCommentMapper;

    @Autowired
    CircleCommentMapperExt circleCommentMapperExt;

    @Autowired
    CircleReplyMapperExt circleReplyMapperExt;

    @Override
    public PageBean<CircleComment> queryAllByPage(int pageNum, int pageSize) {


        PageBean<CircleComment> pageBean = new PageBean<CircleComment>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleCommentMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<CircleComment> lists = circleCommentMapperExt.findByPage(start,size);

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public PageBean<CircleComment> queryManyByPage(String keyword, int pageNum, int pageSize) {



        PageBean<CircleComment> pageBean = new PageBean<CircleComment>();
        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleCommentMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();

        //封装每页显示的数据
        List<CircleComment> lists = circleCommentMapperExt.queryManyByPage(keyword,start,size);

        pageBean.setLists(lists);
        System.out.println(lists.size());

        return pageBean;
    }

    @Override
    public boolean delete(String id) {

        //级联删除---------->先删除底层的------回复层
         circleReplyMapperExt.deleteByCommentId(id);

       //删除评论层
        int count = circleCommentMapper.deleteByPrimaryKey(id);

       return  count>0;
    }
}
