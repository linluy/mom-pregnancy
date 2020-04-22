package com.wyyuand.controller;


/**
 * 登陆界面
 */
import com.wyyuand.domain.Admin;
import com.wyyuand.service.LoginService;
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

@Controller
@RequestMapping("/adminLogin")
public class LoginController {

    @Autowired
    LoginService service;


    @ResponseBody
    @RequestMapping("/login")
    public Map login(Model model, String password, String loginName, String checkcode, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Admin admin = new Admin();
        admin.setAdmName(loginName);
        admin.setAdmPassword(password);
        model.addAttribute("admin", admin);
        boolean success = service.queryUser(admin);


        String sessCheckCode = (String) session.getAttribute("checkcode");
        Map map = new HashMap();
        if("".equals(loginName))
        {
            map.put("msg", "用户名不能为空");
        }else if("".equals(password)){
            map.put("msg", "密码不能为空");
        }else if (sessCheckCode.equalsIgnoreCase(checkcode)) {
            if (success) {
                map.put("success", success);
            } else {
                map.put("msg", "用户名或密码不正确");
            }
        } else {
            map.put("msg", "验证码不正确");
        }
        return map;
    }



}
