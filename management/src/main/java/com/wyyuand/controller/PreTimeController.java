package com.wyyuand.controller;


import com.wyyuand.domain.PreTime;
import com.wyyuand.service.PreTimeService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


//http://127.0.0.1:8080/manage/pretime/list
//http://127.0.0.1:8080/manage/pretime/queryManyByPage
@Controller
@RequestMapping("/pretime")
public class PreTimeController {


    @Autowired
    PreTimeService timeTabService;

//    管理员查询
    //http://127.0.0.1:8080/manage/pretime/adminlist
    @RequestMapping("/listByPage")
    public  String adminList(Model model,String pageNumStr, String pageSizeStr){

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<PreTime> listPage = timeTabService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<PreTime> timeTabs = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("timeTabs",timeTabs);

        return  "/js_time_tab/time_tab_list.jsp";


    }
    //2.管理员修改
    @RequestMapping("/toEdit")
    public  String toEdit(Model model,String id) throws  Exception
    {
        PreTime timeTab = timeTabService.queryById(id);       //查找到要修改的内容
//        System.out.println("========================"+imagesPath);
        model.addAttribute("timeTab",timeTab);
        return "/js_time_tab/time_tab_edit.jsp";                 //跳转到修改页
    }
    //保存修改
    @RequestMapping("/saveEdit")
    public  String saveEdit(PreTime timeTab) throws  Exception
    {

        timeTabService.update(timeTab);
        return "redirect:/pretime/listByPage";        //重定向

    }
    //1.用户查询
//    List<PreTime>
    @ResponseBody
    @RequestMapping("/list")
    public  List<PreTime> list(Model model){
        List<PreTime> timeTabs= timeTabService.queryAll();
        model.addAttribute("timeTabs",timeTabs);     //id  url
        return  timeTabs;
    }
    @RequestMapping("/queryByKeyWord")
    public String queryByKeyWord(Model model , String keyword, HttpServletRequest request, String pageNumStr, String pageSizeStr) throws  Exception{

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<PreTime> listPage = timeTabService.queryManyByPage(keyword,pageNum, pageSize);
//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<PreTime> timeTabs = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("timeTabs",timeTabs);

        return  "/js_time_tab/time_tab_list.jsp";

    }
}
