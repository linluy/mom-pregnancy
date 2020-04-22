package com.wyyuand.controller;

import com.wyyuand.domain.CircleReply;
import com.wyyuand.service.CircleCommentService;
import com.wyyuand.service.CircleReplyService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/circleReply")
public class circleReplyController {

    @Autowired
     CircleReplyService  circleReplyService;

//    http://127.0.0.1:8080/manage/circleReply/listByPage
//    @ResponseBody
    @RequestMapping("/listByPage")
    public String replyList(Model model, String pageNumStr, String pageSizeStr){
//        List<Knowledge> knowledgeList = knowledgeService.selectKnowledgeAdmin();

        int pageNum = 1;          //默认页
        int pageSize = 3;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<CircleReply> listPage = circleReplyService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<CircleReply> circleReplyList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //

        model.addAttribute("circleReplyList",circleReplyList);

        return "/js_circle_reply/circle_reply_list.jsp";
//        return circleReplyList;
    }




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

        PageBean<CircleReply> listPage = circleReplyService.queryManyByPage(keyword,pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<CircleReply> circleReplyList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("circleReplyList",circleReplyList);

        return "/js_circle_reply/circle_reply_list.jsp";

    }


    //回复的帖子删除

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception
    {
        boolean success = circleReplyService.delete(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

//    全删除

    /*删除全部*/
    @ResponseBody
    @RequestMapping("/deleteAll")
    public  Map deleteAll(HttpServletRequest request, HttpServletResponse response, String ids){
        String[] myIds = ids.split(",");
        boolean success  = circleReplyService.deleteAll(myIds);
//        System.out.println(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

}
