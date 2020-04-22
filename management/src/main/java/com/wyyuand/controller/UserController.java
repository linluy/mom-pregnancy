package com.wyyuand.controller;

import com.wyyuand.domain.User;
import com.wyyuand.service.UserService;
import com.wyyuand.utils.BackContent;
import com.wyyuand.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//http://127.0.0.1:8080/manage/validate_login?userEmail=wyyuand@163.com&password=8899Y9I0D2
//http://127.0.0.1:8080/manage/ajaxDel?userEmail=wyyuand@163.com&password=8899Y9I0D2
@Controller
public class UserController {

    Boolean success = false;
    public static final String UPLOAD_DIR = "d:\\upload\\user\\";   //想放在哪个目录下

    @Autowired
    UserService userService;

//1、用户登录验证
    @ResponseBody
    @RequestMapping("/validate_login")
    public User login(HttpSession session,String userEmail,String password) throws Exception {
        User user = new User();
        user.setUserEmail(userEmail);
        user.setUserPassword(password);
        User selectUser = userService.queryByEmail(user);
        return  selectUser;
    }

  ///http://127.0.0.1:8080/manage/queryAll
    //用户注册
    @ResponseBody
    @RequestMapping("/save")
    public String save(HttpSession session, String username,String password,String btnSex,String birthday,String email,String predate) throws Exception {
        String register = "0";
        User user = new User();
        user.setUserName(username);
        user.setUserPassword(password);
        user.setUserBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        user.setUserEmail(email);
        user.setUserSex(btnSex);
        user.setUserPredate(new SimpleDateFormat("yyyy-MM-dd").parse(predate));
        success = userService.save(user);
        if(success){
            register ="1";
        }
        System.out.println("success:  开始之后："+success);
        String xml = BackContent.callBack(register);
        return  xml;
    }

    //用户查询
    @ResponseBody
    @RequestMapping("/queryAll")
    public List queryAll(HttpSession session,String userEmail,String password) throws Exception {
        List<User> list = userService.queryAll();
        return  list;             //查询所有用户
    }

    //http:127.0.0.1:8080/manage/updateUser        user内容是最新的----用户修改    传来一个user类型的
    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(User user) throws Exception {

        String isSave = "0";

        success = userService.update(user);

        if(success){
            isSave = "1";
        }
        System.out.println(success);

        return "1";
    }


    @RequestMapping("/download")
    public void download(String id, HttpServletResponse response) throws Exception {

        User user = userService.queryById(id);
        String fileName = user.getUserPic();                                   //这里得到的是用户的头像
        if((fileName != null) && (!"".equals(fileName))){

            FileInputStream is = null;
            //得到输出流
            System.out.println("fileName: "+ fileName);
            OutputStream output = response.getOutputStream();
            //读取文件流
            is = new FileInputStream(new File(UPLOAD_DIR + fileName));
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

    @RequestMapping("/downloadBaby")
    public void downloadBaby(String id, HttpServletResponse response) throws Exception {

        User user = userService.queryById(id);
        String fileName = user.getBabyPic();                                   //这里得到的是用户的头像
        if((fileName != null) && (!"".equals(fileName))){

            FileInputStream is = null;
            //得到输出流
            System.out.println("fileName: "+ fileName);
            OutputStream output = response.getOutputStream();
            //读取文件流
            is = new FileInputStream(new File(UPLOAD_DIR + fileName));
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

    //

    @RequestMapping("/queryUser")
    public String queryUser(HttpSession session, Model model,String pageNumStr, String pageSizeStr) throws Exception {

//        List<Knowledge> knowledgeList = knowledgeService.selectKnowledgeAdmin();

        int pageNum = 1;          //默认页
        int pageSize = 5;       //和默认的页长度

        if(pageNumStr != null && !"".equals(pageNum)){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null && !"".equals(pageSize)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<User> listPage = userService.queryAllByPage(pageNum, pageSize);

//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<User> userList = listPage.getLists();

        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //
        model.addAttribute("userList",userList);

        return  "/js_user/user_list.jsp";             //查询所有用户

    }

    @ResponseBody
    @RequestMapping("/ajaxDel")
    public Map ajaxDel(String id) throws  Exception {
        boolean success = userService.delete(id);

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

        PageBean<User> listPage = userService.queryManyByPage(keyword,pageNum, pageSize);
//        model.addAttribute("knowledgeList",knowledgeList);
        int currPage = listPage.getCurrPage();          ////当前页数 pageNum
        int totalCount = listPage.getTotalCount();     //总记录数  rowCount
        int  totalPage = listPage.getTotalPage() ;    //总页数  pageCount

        List<User> userList = listPage.getLists();
        model.addAttribute("pageNum",currPage);
        model.addAttribute("rowCount",totalCount);      //rowCount
        model.addAttribute("pageCount",totalPage);      //

        model.addAttribute("userList",userList);

        return  "/js_user/user_list.jsp";             //查询所有用户
    }
}
