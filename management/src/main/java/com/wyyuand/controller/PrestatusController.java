package com.wyyuand.controller;

import com.wyyuand.domain.Knowledge;
import com.wyyuand.domain.Prestatus;
import com.wyyuand.service.PrestatusService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//孕周管理变化
//http://127.0.0.1:8080/manage/prestatus/queryByPeriod?period=1
@Controller
@RequestMapping("/prestatus")
public class PrestatusController {

    public static final String UPLOAD_DIR = "D:\\upload\\baby\\";   //想放在哪个目录下

    private   String imagesPath;

    @Autowired
    PrestatusService prestatusService ;

    private static Map<String,String> imageTypePathmap = new HashMap();

    /*
    *
    *后台 -------》
    * */

    @RequestMapping("/listByPage")
    public String queryByPage(Model model,String pageNumStr, String pageSizeStr){
     /*   List<Prestatus> prestatuses = new ArrayList<>();
        return prestatuses;*/
        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Prestatus> listPage = prestatusService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Prestatus> prestatuses = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("prestatuses",prestatuses);


        return "/js_prestatus/prestatus_list.jsp";
    }
    // 获取要修改的内容
    @RequestMapping("/toEdit")
    public  String toEdit(Model model,String id) throws  Exception {

        Prestatus prestatus = prestatusService.queryById(id);       //查找到要修改的内容

        imagesPath = prestatus.getPicture();
        System.out.println("========================" + imagesPath);
        model.addAttribute("prestatus", prestatus);

        return "/js_prestatus/prestatus_edit.jsp";         //跳转到修改页

    }
    //    内容修改
    @RequestMapping("/saveEdit")
    public  String saveEdit(Prestatus prestatus, MultipartFile pics) throws  Exception
    {

        System.out.println( "----------------- "+ pics.getOriginalFilename());
        String org = null;
        System.out.println(pics);

        if(!pics.getOriginalFilename().equals("")){
            org = pics.getOriginalFilename();           //获取上传的文件名字
        }else{
            org = imagesPath;
        }

        prestatusService.update(prestatus,org);
        return "redirect:/prestatus/listByPage";        //重定向
    }
    //http://192.168.172.1:8080/manage/prestatus/queryByPeriod?period=1
    //根据周期查找数据
    @ResponseBody
    @RequestMapping("/queryByPeriod")
    public Prestatus queryByPeriod(String period){

        Prestatus prestatus = null;
        prestatus = prestatusService.queryByPeriod(period);
        return  prestatus;            //得到值
    }
    //查找全部数据
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

        PageBean<Prestatus> listPage = prestatusService.queryManyByPage(keyword,pageNum, pageSize);
//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Prestatus> prestatuses = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("prestatuses",prestatuses);

        return "/js_prestatus/prestatus_list.jsp";

    }
    @RequestMapping("/download")
    public void download(String id, HttpServletResponse response) throws IOException {

        Prestatus prestatus = prestatusService.queryById(id);    //此时id为0

        String fileName = prestatus.getPicture();
        FileInputStream is = null;
        //得到输出流
        System.out.println("fileName: "+ fileName);
        OutputStream output = response.getOutputStream();
        //读取文件流
        is = new FileInputStream(new File(UPLOAD_DIR +fileName));
        //输入缓冲流
        BufferedInputStream bis = new BufferedInputStream(is);
        //输出缓冲流
        BufferedOutputStream bos = new BufferedOutputStream(output);
        // 缓冲字节数
        byte data[] = new byte[1024];
        int size = 0;
        size = bis.read(data);
        while (size != -1) {
            bos.write(data, 0, size);
            size = bis.read(data);
        }
        bis.close();
        bos.flush();
        bos.close();
        output.close();
    }

}
