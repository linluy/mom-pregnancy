package com.wyyuand.service;

import com.wyyuand.domain.Circle;
import com.wyyuand.domain.CircleComment;
import com.wyyuand.utils.PageBean;

import java.util.List;

public interface CircleService {

    /*
    * android
    * */
    public List<Circle> queryAllCircle();
    public boolean insertPost( String userId, String circleTheme, String circleContent);
    public boolean insertComment(String userId, String userName,String userLogo,String circleId, String commentContent);     //插入评论用户的id，name，pic，评论的内容，时间
    public boolean insertReply(String userId, String userName,String userLogo,String commentId, String commentContent);     //插入评论用户的id，name，pic，评论的内容，时间
    public List<CircleComment> queryCommentByCircleid(String circleId);

/*
后台
* */
    public PageBean<Circle> queryAllByPage(int pageNum, int pageSize);
    public PageBean<Circle> queryManyByPage(String keyword,int pageNum, int pageSize);
    public  boolean delete(String id);


}
