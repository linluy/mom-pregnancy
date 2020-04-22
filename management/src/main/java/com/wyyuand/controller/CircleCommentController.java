package com.wyyuand.controller;

import com.wyyuand.domain.CircleComment;
import com.wyyuand.service.CircleCommentService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/circleComment")
public class CircleCommentController {

    @Autowired
    CircleCommentService circleCommentService;

//http://127.0.0.1:8080/manage/circleComment/listByPage

    @RequestMapping("/listByPage")
    public String commentList(Model model, String pageNumStr, String pageSizeStr){
//        List<Knowledge> knowledgeList = knowledgeService.selectKnowledgeAdmin();

        int pageNum = 1;          //默认页
        int pageSize = 8;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<CircleComment> listPage = circleCommentService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<CircleComment> circleCommentList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //

        model.addAttribute("circleCommentList",circleCommentList);

        return "/js_circle_comment/circle_comment_list.jsp";
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

        PageBean<CircleComment> listPage = circleCommentService.queryManyByPage(keyword,pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<CircleComment> circleCommentList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("circleCommentList",circleCommentList);

        return "/js_circle_comment/circle_comment_list.jsp";

    }

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception
    {
        boolean success = circleCommentService.delete(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }
}
