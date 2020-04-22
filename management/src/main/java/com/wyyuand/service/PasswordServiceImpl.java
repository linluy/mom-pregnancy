package com.wyyuand.service;


import com.wyyuand.Mail;
import com.wyyuand.dao.AdminMapper;
import com.wyyuand.dao.UserMapper;
import com.wyyuand.dao.UserMapperExt;
import com.wyyuand.domain.Admin;
import com.wyyuand.domain.User;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeBodyPart;
import java.util.List;

@Service
public class PasswordServiceImpl implements PasswordService {

    boolean success = false;

    @Autowired
    AdminMapper adminDao;

    @Autowired
    UserMapper userDao;
    @Autowired
    UserMapperExt userDaoExt;

    @Autowired
    UserService userService;

    String smtp = "smtp.163.com";     //SMTP服务器地址
    //发件人邮箱
    String from = "wyyuand@163.com";
    //收件人邮箱  从数据库中的到
//    String to = "1819697566@qq.com";
    String to = null;
    //抄送人邮箱
    String copyto = "1819697566@qq.com,wyyuand@163.com";
    //主题
    String subject = "申请密码找回";
    MimeBodyPart img = new MimeBodyPart();
//    String content = "<div style='color:blue;font-size:18px;'>请点击以下链接进行重置密码</div>";
//    String content = "";
    //正文中的图片
    String imgFile = "C:/Users/沐沐/Pictures/Camera Roll/timg (1).jpg";
    //附件
    String filename = "C:/Users/沐沐/Pictures/Camera Roll/timg (1).jpg";

    //163邮箱用户名就是去掉@163.com
    String username = "wyyuand";
    String password = "yy0829";

    @Override
    public void GetMail(String name,String email,String status){
        String content = "";
        to = email;

        if("1".equals(status)){         //用户链接
//            content+="<a href='http://127.0.0.1:8080/manage/login_register/item_forgetPassword2.jsp'>点击链接</a>";
            //根据用户名修改密码
            User user = new User();
            String password = StringUtil.createRandomNum(10);
            user.setUserPassword(password);    //系统随机生成密码
            user.setUserName(name);
            user.setUserEmail(email);

            try {
                success = userService.updatePassword(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            content+="<h3>您的密码已重置为"+password+"，请使用此密码登陆，后尽快修改为新的密码</h3>";

        }else if("0".equals(status)){      //管理员
            content+="<a href='http://127.0.0.1:8080/manage/login_register/item_forgetPassword2.jsp'>点击链接,重置密码</a>";
        }
        System.out.println(to);
        // Mail.send(smtp,from,to,subject,content,username,password);
//        Mail.sendAndCcWithFile(smtp, from, to, subject, content, imgFile, username, password, copyto, filename);
        Mail.sendAndCcWithFile(smtp, from, to, subject, content, imgFile, username, password, copyto, filename);
    }

    @Override
    public boolean queryEmail(Admin adminEN) {

        List<Admin> list = adminDao.selectByExample(null);
        boolean success = false;
        String DBadmName;
        String DBadmEmail;
        for (Admin admin : list) {
            /* System.out.println(admin1.getAdmName());*/
            DBadmName = admin.getAdmName();
            DBadmEmail = admin.getAdmEmail();
            if(DBadmName.equals(adminEN.getAdmName()) && DBadmEmail.equals(adminEN.getAdmEmail())) {
                success = true;
                break;
            }
        }
        return success;
    }


    @Override
    public boolean queryUserEmail(User userEN) {

        boolean success = false;
        int count = userDaoExt.queryEmailUser(userEN);
        if(count > 0){
            success = true;
        }else{
            success = false;
        }
        return  success;
    }
}
