package com.wyyuand.controller;


import com.wyyuand.domain.BabyWeight;
import com.wyyuand.domain.Food;
import com.wyyuand.service.BabyWeightService;
import com.wyyuand.utils.PageBean;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

//http://127.0.0.1:8080/manage/babyWeight/queryCulWeight?userId=32442dfsd
//http://127.0.0.1:8080/manage/babyWeight/listByPage
@Controller
@RequestMapping("/babyWeight")
public class BabyWeightController {

    @Autowired
    BabyWeightService babyWeightService;

//    listByPage
    @RequestMapping("/listByPage")
    public String listByPage(String pageNumStr, String pageSizeStr ,Model  model) {

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if (pageNumStr != null && !"".equals(pageNum)) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        if (pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //调用service层的方法
        PageBean<BabyWeight> listPage = babyWeightService.queryAllByPage(pageNum, pageSize);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int totalPage = listPage.getTotalPage();    //总页数  pageCount

        List<BabyWeight> babyWeightList = listPage.getLists();
        model.addAttribute("pageNum", currPage);
        model.addAttribute("rowCount", totalCount);      //rowCount
        model.addAttribute("pageCount", totalPage);      //
        model.addAttribute("babyWeightList", babyWeightList);

        System.out.println("---------------------"+babyWeightList.size());
        System.out.println("---------------------"+babyWeightList.size());
        System.out.println("---------------------"+babyWeightList.size());
        System.out.println("---------------------"+babyWeightList.size());

        return "/js_baby_weight/baby_weight_list.jsp";              //内部跳转（带数据）
    }
/*
*
* 展示给用户
*
* */
    //根据用户id查找数据
    @ResponseBody
    @RequestMapping("/queryCulWeight")
    public List<BabyWeight> queryCulWeight(String userId){
        List<BabyWeight> weightList = babyWeightService.queryCulWeight(userId);
        return  weightList;
    }

//    http://127.0.0.1:8080/manage/babyWeight/insertWeight
    //根据用户id增加数据

//babyWeight/insertWeight
    @ResponseBody
    @RequestMapping("/insertWeight")
    public String insertWeight(String userId,String ACStr,String BDPStr,String FLStr){

        String  isSave= "0";
        boolean success = babyWeightService.insertBabyWeight(userId,ACStr,BDPStr,FLStr);

        if(success){
            System.out.println("后端验证通过，，，");
            isSave = "1";
        }else{
            isSave = "2";
        }
        return isSave;
    }

    //根据每条信息的weight_id 进行宝宝的删除
    @ResponseBody
    @RequestMapping("/deleteWeight")
    public String deleteWeight(String weightId){

        String  isDelete= "0";
        boolean success = babyWeightService.deleteBabyWeight(weightId);

        if(success){
            System.out.println("后端验证通过，，，");
            isDelete = "1";
        }
        return isDelete;
    }

    /*
    *
    * 后台部分----------->分页查看录入的宝宝体重
    * */

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception
    {
        boolean success = babyWeightService.deleteBabyWeight(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }


    /*删除全部*/
    @ResponseBody
    @RequestMapping("/deleteAll")
    public  Map deleteAll(HttpServletRequest request, HttpServletResponse response, String ids){
        String[] myIds = ids.split(",");
        boolean success  = babyWeightService.deleteAll(myIds);
//        System.out.println(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
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

        PageBean<BabyWeight> listPage = babyWeightService.queryManyByPage(keyword,pageNum, pageSize);
//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<BabyWeight> babyWeightList = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("babyWeightList", babyWeightList);

        return "/js_baby_weight/baby_weight_list.jsp";              //内部跳转（带数据）

    }
}
