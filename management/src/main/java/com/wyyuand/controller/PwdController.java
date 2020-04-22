package com.wyyuand.controller;

import com.wyyuand.domain.Admin;
import com.wyyuand.domain.User;
import com.wyyuand.service.AdminService;
import com.wyyuand.service.PasswordService;
import com.wyyuand.service.UserService;
import com.wyyuand.utils.BackContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


//忘记密码
@Controller
@RequestMapping("/pwd")
public class PwdController {

    String admNameTmp;
    String admEmailTmp;

    boolean success = false;

    @Autowired
    PasswordService passwordService;
    @Autowired
    AdminService adminService;


    @Autowired
    UserService userService;

//    接到用户输入的用户名和邮件地址进行核对,如果正确,发送重置密码邮件,不正确,返回信息:邮件不正确
    @ResponseBody
    @RequestMapping("/getMail")
    public Map pwd(Model model,String admName, String admEmail) throws Exception {
        admNameTmp =admName;
        admEmailTmp =admEmail;
        model.addAttribute("admName",admName);
        model.addAttribute("admEmail",admEmail);

        Admin admin = new Admin();
        admin.setAdmName(admName);
        admin.setAdmEmail(admEmail);
        //携带数据
        Map map = new HashMap();
        if("".equals(admName))
        {
            map.put("msg", "用户名不能为空");
        }else if("".equals(admEmail)){

            map.put("msg", "邮箱不能为空");

        }else{
            success = passwordService.queryEmail(admin);
            System.out.println(success);
            if (success) {
                passwordService.GetMail(admName,admEmail,"0");   // //管理员箱。。。0
                map.put("success",true);
            } else {
                map.put("msg", "用户名或邮箱不正确");
            }
        }
        //获得邮箱进行发送
        return map;
    }


    @ResponseBody
    @RequestMapping("/reSet")
    public Map reSet(Model model,String admName, String admPasswordOne,String admPasswordTwo) throws Exception {

        model.addAttribute("admName",admName);
        model.addAttribute("admPasswordOne",admPasswordOne);
        Admin admin = new Admin();

        admin.setAdmName(admName);
        admin.setAdmPassword(admPasswordOne);
        admin.setAdmEmail(admEmailTmp);

        //携带数据
        Map map = new HashMap();
        if("".equals(admName))
        {
            map.put("msg", "用户名不能为空");
        }else if("".equals(admPasswordOne) || "".equals(admPasswordTwo)){
            map.put("msg", "请输入密码");
        }else if(!admNameTmp.equals(admName)) {
            map.put("msg", "用户名不正确");
        }else if(!admPasswordOne.equals(admPasswordTwo)){
            map.put("msg", "密码不一致");
        }else{
                //根据邮箱更新密码
//                System.out.println("hhhhhhhhhhh");
//                success = adminService.updatePassword(admPasswordOne,admEmailTmp);
                success = adminService.updatePassword(admin);
                System.out.println(success);

                if(success){
                    map.put("success",success);
                }else{
                    map.put("msg","修改失败");
                }
        }
        //获得邮箱进行发送
        return map;
    }

    //android端 用户重置密码。。。。
    //1 . 查数据库用户名邮箱是否匹配
    @ResponseBody
    @RequestMapping("/pwdUser")
    public String  pwdUser(HttpSession sessionId, String userName, String userEmail) throws Exception {
        String sucessEmail = "0";
        User user = new User();
        user.setUserName(userName);
        user.setUserEmail(userEmail);
        if (!("".equals(userName) || "".equals(userEmail))) {
            success = passwordService.queryUserEmail(user);
//            System.out.println("success: " +success);
            if (success) {
                passwordService.GetMail(userName, userEmail, "1");   // //管理员箱。。。0
                sucessEmail = "1";
            }
            System.out.println(success);
        }
        System.out.println("success:  开始之后："+success);
        String xml = BackContent.callBack(sucessEmail);
        return  xml;
    }

}
