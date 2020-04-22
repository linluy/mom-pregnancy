package com.wyyuand.controller;


import com.wyyuand.domain.Admin;
import com.wyyuand.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员具体功能
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService admService;

    @RequestMapping("/list")
    public void list() throws Exception{
        admService.queryAll();
        //System.out.println("AdminController.list,正常访问");    //spring正常访问
    }
///admin
    @ResponseBody
    @RequestMapping("/save")
    public Map save(Admin admin) throws Exception{
      //  System.out.println("success");
        boolean success = admService.save(admin);
        Map map = new HashMap();
        if(success){
            //System.out.println("success");
            map.put("success",success);
        } else
        {
            map.put("msg","注册失败");
        }
//        System.out.println("AdminController.list,正常访问");    //spring正常访问
        return map;
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map login(Model model, String password, String admEmail, String checkcode, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        boolean success = false;
        Admin admin = new Admin();

        String sessCheckCode = (String) session.getAttribute("checkcode");
        Map map = new HashMap();
        if("".equals(admEmail) && admEmail != null)
        {
            map.put("msg", "邮箱不能为空");
        }else if("".equals(password) && password!=null){
            map.put("msg", "密码不能为空");
        }else{
            admin = admService.queryByEmail(admEmail,password);
            if(admin != null){
                success = true;
            }else{
                success = false;
            }
        }
        if (sessCheckCode.equalsIgnoreCase(checkcode)) {
            if (success) {
                map.put("success", success);
            } else {
                map.put("msg", "用户名或密码不正确");
            }
        } else {
            map.put("msg", "验证码不正确");
        }
        session.setAttribute("LoginUser",admin.getAdmName());
        session.setAttribute("adminId",admin.getAdmId());
        model.addAttribute("admin", admin);
        return map;
    }

    @RequestMapping("/adminInfo")
    public String queryAdminInfo(Model model,String adminId) throws Exception {
        Admin admin = admService.queryById(adminId);

        model.addAttribute("admin",admin);
        return  "/js_admin/admin_list.jsp";
    }

    @ResponseBody
    @RequestMapping("/updateAdm")
    public Map updateAdm(Admin admin) throws Exception {

       /* System.out.println("------------------"+admin.getAdmDisplayName());
        System.out.println("------------------"+admin.getAdmName());
        System.out.println("------------------"+admin.getAdmEmail());
        System.out.println("------------------"+admin.getAdmPassword());
        System.out.println("------------------"+admin.getAdmSex());
        System.out.println("------------------"+admin.getAdmId());
        System.out.println("------------------"+admin.getAdmStatus());*/

        boolean success = admService.update(admin);
        Map map = new HashMap();
        if(success){
            //System.out.println("success");
            map.put("success",success);
        } else
        {
            map.put("msg","更新失败");
        }
//        System.out.println("AdminController.list,正常访问");    //spring正常访问
        return map;
    }
}

