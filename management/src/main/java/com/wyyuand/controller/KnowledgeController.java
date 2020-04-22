package com.wyyuand.controller;


import com.wyyuand.domain.Knowledge;
import com.wyyuand.service.KnowledgeService;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 孕期知识
 */
//http://127.0.0.1:8080/manage/knowledge/list
//http://127.0.0.1:8080/manage/knowledge/download?fileName="123"
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {

    public static final String UPLOAD_DIR = "d:\\upload\\knowledge\\";   //想放在哪个目录下
    private   String imagesPath;
    @Autowired
    KnowledgeService knowledgeService;

/*
* 展示给用户用的
* */
//     List<Knowledge>
    @ResponseBody
    @RequestMapping("/list")
    public List<Knowledge> list(Model model){

        List<Knowledge> knowledgeList = knowledgeService.selectKnowledge();
        return knowledgeList;
    }
/*
* 管理员一端
* */
    @RequestMapping("/listByPage")
    public String adminList(Model model,String pageNumStr, String pageSizeStr){

//        List<Knowledge> knowledgeList = knowledgeService.selectKnowledgeAdmin();

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }

        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Knowledge> listPage = knowledgeService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();             ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();       //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;        //总页数  pageCount

        List<Knowledge> knowledgeList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("knowledgeList",knowledgeList);

        return "/js_knowledge/knowledge_list.jsp";

    }

    /*删除全部*/
    @ResponseBody
    @RequestMapping("/deleteAll")
    public  Map deleteAll(HttpServletRequest request, HttpServletResponse response, String ids){
        String[] myIds = ids.split(",");
        boolean success  = knowledgeService.deleteAll(myIds);
//        System.out.println(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception
    {
        boolean success = knowledgeService.delete(id);
        Map map = new HashMap();
        map.put("success",success);
        return map;
    }

//    添加内容
   @RequestMapping("/save")
    public String save(Knowledge knowledge, MultipartFile pics){

        String org = pics.getOriginalFilename();           //获取上传的文件名字
        boolean success = knowledgeService.save(knowledge,org);
        System.out.println("ContentController.insert: " + success);
        return "redirect:/knowledge/listByPage";

    }

   // 获取要修改的内容
    @RequestMapping("/toEdit")
    public  String toEdit(Model model,String id) throws  Exception
    {
        Knowledge knowledge = knowledgeService.queryById(id);       //查找到要修改的内容

        imagesPath = knowledge.getImages();
        System.out.println("========================"+imagesPath);
        model.addAttribute("knowledge",knowledge);
        return "/js_knowledge/knowledge_edit.jsp";         //跳转到修改页
    }

//    内容修改
    @RequestMapping("/saveEdit")
    public  String saveEdit(Knowledge knowledge, MultipartFile pics) throws  Exception
    {

        System.out.println( "----------------- "+ pics.getOriginalFilename());
        String org = null;
        System.out.println(pics);

        if(!pics.getOriginalFilename().equals("")){
            org = pics.getOriginalFilename();           //获取上传的文件名字
        }else{
            org = imagesPath;
        }

        knowledgeService.update(knowledge,org);
        return "redirect:/knowledge/listByPage";        //重定向
    }
//http://127.0.0.1:8080/manage/knowledge/queryByKeyWord?keyword=产检
    @RequestMapping("/queryByKeyWord")
    public String queryByKeyWord(Model model ,String keyword, HttpServletRequest request,String pageNumStr, String pageSizeStr) throws  Exception{

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Knowledge> listPage = knowledgeService.queryManyByPage(keyword,pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<Knowledge> knowledgeList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("knowledgeList",knowledgeList);

        return "/js_knowledge/knowledge_list.jsp";
//        return  knowledgeList;

    }

    @RequestMapping("/download")
    public void download(String id,HttpServletResponse response) throws IOException {

        Knowledge knowledge = knowledgeService.queryById(id);
        String imagesPath =  knowledge.getImages();
//        String fileName = food.getPicture();
        FileInputStream is = null;
        //得到输出流
        System.out.println("imagesPath:--------------------- "+ imagesPath);
        OutputStream output = response.getOutputStream();
        //读取文件流
        is = new FileInputStream(new File(UPLOAD_DIR + imagesPath));
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
