package com.wyyuand.service;


import com.wyyuand.dao.*;
import com.wyyuand.domain.Circle;
import com.wyyuand.domain.CircleComment;
import com.wyyuand.domain.CircleReply;
import com.wyyuand.domain.User;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CircleServiceImpl implements  CircleService {

    @Autowired
    CircleMapper circleMapper;
    @Autowired
    CircleMapperExt circleMapperExt;

    @Autowired
    CircleCommentMapper circleCommentMapper;
    @Autowired
    CircleCommentMapperExt circleCommentMapperExt;

    @Autowired
    CircleReplyMapper circleReplyMapper ;
    @Autowired
    CircleReplyMapperExt circleReplyMapperExt;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Circle> queryAllCircle() {
        List<Circle> circles = null;
        try {
            circles =   circleMapperExt.findUserWithCircle();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int i = 0; i <circles.size() ; i++) {
            //从数据库中查找  id 的评论者
            String circleId =  circles.get(i).getCircleId(); //得到数据库中帖子的id----- 查找 评论表中id相同的数据
            //查询
            try {
                count = circleMapperExt.findCommentCount(circleId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            circles.get(i).setFllowNum(count);
        }
        return circles;
    }

    @Override
    public boolean insertPost( String userId, String circleTheme, String circleContent) {
        Circle circle = new Circle();

        circle.setCircleId("id"+StringUtil.createRandomNum(5));
        circle.setUserId(userId);
        circle.setCircleContent(circleContent);
        circle.setCirclePublicTime(new Date());
        circle.setCircleTheme(circleTheme);
        int count = circleMapper.insertSelective(circle);
        return count>0;
    }

    @Override
    public boolean insertComment(String userId, String userName,String userLogo,String circleId, String commentContent) {

        //根据Id 从User里面查数据
        CircleComment circleComment = new CircleComment();

        User user = userMapper.selectByPrimaryKey(userId);
        userLogo = user.getUserPic();
        userName = user.getUserName();

        circleComment.setId("com"+StringUtil.createRandomNum(5));
        circleComment.setUserId(userId);
        circleComment.setUserLogo(userLogo);
        circleComment.setNickName(userName);

        circleComment.setCreateDate(new Date());
        circleComment.setContent(commentContent);
        circleComment.setCircleId(circleId);

        int count = circleCommentMapper.insertSelective(circleComment);
        return count>0;
    }

    @Override
    public boolean insertReply(String userId, String userName, String userLogo, String commentId, String commentContent) {
        CircleReply circleReply = new CircleReply();

        circleReply.setReplyId("rep"+StringUtil.createRandomNum(5));
        circleReply.setUserId(userId);
        circleReply.setUserLogo(userLogo);
        circleReply.setNickName(userName);
        circleReply.setCommentId(commentId);
        circleReply.setContent(commentContent);
        circleReply.setCreateDate(new Date());
        circleReply.setStatus("0");
        int count = circleReplyMapper.insertSelective(circleReply);
        return count>0;

    }

    @Override
    public List<CircleComment> queryCommentByCircleid(String circleId) {
        //根据每个帖子的id进行查询   1 ---->n  的关系   要的结果是一个id 下面是一堆评论 和回复
        List<CircleComment> circleComments = null;
        try {
           circleComments =  circleMapperExt.findCommentWithCircle(circleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return circleComments;
    }


    @Override
    public PageBean<Circle> queryAllByPage(int pageNum, int pageSize) {

        PageBean<Circle> pageBean = new PageBean<Circle>();

        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleMapperExt.queryAllCount();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();
        //封装每页显示的数据
        List<Circle> lists = circleMapperExt.findByPage(start,size);

        for (Circle list : lists) {
            System.out.println("type---------->"+list.getCircleId());
        }

        System.out.println(lists.size());
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public PageBean<Circle> queryManyByPage(String keyword, int pageNum, int pageSize) {

        PageBean<Circle> pageBean = new PageBean<Circle>();
        //封装当前页数
        pageBean.setCurrPage(pageNum);

        //每页显示的数据
        pageBean.setPageSize(pageSize);

        //封装总记录数
        int totalCount = circleMapperExt.queryAllByKeyCount(keyword);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());

        int start = (pageNum-1)*pageSize;
        int size = pageBean.getPageSize();

        //封装每页显示的数据
        List<Circle> lists = circleMapperExt.queryManyByPage(keyword,start,size);

        pageBean.setLists(lists);
        System.out.println(lists.size());

        return pageBean;
    }

    @Override
    public boolean delete(String id) {

        //级联删除 ----------- 先删评论
        //先查找 评论的 id
        CircleComment circleComment = new CircleComment();
        List<CircleComment> list = circleCommentMapperExt.selectCommentId(id);
        String[] ids = new String[list.size()];                          //ids 就是评论的id-------调用底层进行删除
        for (int i = 0; i < list.size() ; i++) {

            circleComment = list.get(i);
            ids[i] = circleComment.getId();
        }
        if(list.size() != 0){
            int count1 = circleReplyMapperExt.deleteAllByCommentId(ids); //将回复删除
            System.out.println("count---------->"+(count1>0));
        }
        //删除评论
        int count2 = circleCommentMapperExt.deleteByCircleId(id);
        System.out.println("count---------->"+(count2>0));

        //删除帖子
        int count = circleMapper.deleteByPrimaryKey(id);
        return count>0;
    }


}
