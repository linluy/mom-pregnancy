package com.wyyuand.controller;

import com.wyyuand.domain.Circle;
import com.wyyuand.domain.CircleComment;
import com.wyyuand.domain.User;
import com.wyyuand.service.CircleCommentService;
import com.wyyuand.service.CircleService;
import com.wyyuand.service.UserService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//http://127.0.0.1:8080/manage/circle/listByPage
@Controller
@RequestMapping("/circle")
public class CircleController {

    @Autowired
    CircleService circleService;

    @Autowired
    UserService userService;

    /*
    *
    *
    * android
    *
    * */
    @ResponseBody
    @RequestMapping("/queryAllCircle")
    public List<Circle> queryAllCircle(){
        List<Circle> circles = circleService.queryAllCircle();
        return  circles;
    }

    //http://127.0.0.1:8080/manage/circle/insertPost?userId=602571c5703d49fa8aeacf1878ef0903&circleTheme=你好呀&circleContent=我是一只小小鸟
//    http://127.0.0.1:8080/manage/circle/queryCommentByCircleid?circleId=id632ds
    //插入的是帖子
    @ResponseBody
    @RequestMapping("/insertPost")
    public String insertPost( String userId, String circleTheme, String circleContent){


        String  isSave= "0";
        boolean success = circleService.insertPost(userId, circleTheme, circleContent);

        if(success){
            System.out.println("后端验证通过，，，");
            isSave = "1";
        }
        return isSave;
    }

    //根据帖子的id来查找评论的人数  circle_id
    @ResponseBody
    @RequestMapping("/queryCommentByCircleid")
    public Map queryCommentByCircleid(String circleId){

        List<CircleComment> circleComments = circleService.queryCommentByCircleid(circleId);

        Map map = new HashMap();
        map.put("code","1000");
        map.put("message","查看评论成功");
        Map  data = new HashMap();
        data.put("total",circleComments.size());
        List list = new ArrayList();
        list.add(circleComments);
        data.put("list",circleComments);
        map.put("data",data);
        return  map;

    }

    //插入评论的信息
    //http://127.0.0.1:8080/manage/circle/insertComment?userId=32442dfsd&circleIdid12345&commentContent=嗯，新添加的评论
    @ResponseBody
    @RequestMapping("/insertComment")
    public String insertComment(  String userId,String circleId,String commentContent) throws Exception {

        boolean success = false;
        System.out.println("commentContent-----"+commentContent);
        User user = userService.queryById(userId);
        String userName = user.getUserName();         //得到用户的姓名
        String userLogo = user.getUserPic();       //得到用户的头像
        String  isSave= "0";
        if(commentContent != null){
            success = circleService.insertComment(userId,userName,userLogo,circleId,commentContent);
        }
        if(success){
            System.out.println("后端验证通过，，，");
            isSave = "1";
        }
        return isSave;
    }
    //插入回复的信息
    //http://127.0.0.1:8080/manage/circle/insertReply?userId=32442dfsd&commentId=com678&replyContent=嗯，新添加的回复
    @ResponseBody
    @RequestMapping("/insertReply")
    public String insertReply(  String userId,String commentId,String replyContent) throws Exception {

        boolean success = false;
        System.out.println("commentContent-----"+replyContent);
        User user = userService.queryById(userId);
        String userName = user.getUserName();         //得到用户的姓名
        String userLogo = user.getUserPic();       //得到用户的头像
        String  isSave= "0";

        if(replyContent != null){
            success = circleService.insertReply(userId,userName,userLogo,commentId,replyContent);
        }
        if(success){
            System.out.println("后端验证通过，，，");
            isSave = "1";
        }
        return isSave;

    }

/*
* 后台 /http://127.0.0.1:8080/manage/circle/listByPage
* */
    @RequestMapping("/listByPage")
    public String circleList(Model model, String pageNumStr, String pageSizeStr){

        int pageNum = 1;          //默认页
        int pageSize = 8;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Circle> listPage = circleService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Circle> circleList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("circleList",circleList);

        return "/js_circle/circle_list.jsp";

    }

    //后台的帖子删除

    @RequestMapping("/queryByKeyWord")
    public String queryByKeyWord(Model model , String keyword, HttpServletRequest request, String pageNumStr, String pageSizeStr) throws  Exception{


        int pageNum = 1;          //默认页
        int pageSize = 8;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Circle> listPage = circleService.queryManyByPage(keyword,pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Circle> circleList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("circleList",circleList);

        return "/js_circle/circle_list.jsp";

    }
    //http://127.0.0.1:8080/manage/circle/ajaxDelAdm?circleId=idr4f08
/*
    删除单个的信息
* */
    @ResponseBody
    @RequestMapping("/ajaxDelAdm")
    public Map ajaxDel(String circleId) throws  Exception
    {
        boolean success = circleService.delete(circleId);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

}
