package com.wyyuand;

import org.junit.Test;

import javax.mail.internet.MimeBodyPart;

import static org.junit.Assert.*;

public class MailTest {

    @Test
    public void main() {

        String smtp = "smtp.163.com";     //SMTP服务器地址
        //发件人邮箱
        String from = "wyyuand@163.com";
        //收件人邮箱
        String to = "wyyuand@163.com";
        //抄送人邮箱
        String copyto = "1819697566@qq.com,wyyuand@163.com";
        //主题
        String subject = "13888888888";
        MimeBodyPart img = new MimeBodyPart();
        String content = "<div style='color:red;font-size:18px;'><a href='13888888888' target='_blank'>密码已重置为1231sdhfasojdfhsaldfhasl，请使用此密码登陆，后尽快修改为新的密码</a></div>";
        //正文中的图片
        String imgFile = "C:/Users/沐沐/Pictures/Camera Roll/timg (1).jpg";
        //附件
        String filename = "C:/Users/沐沐/Pictures/Camera Roll/timg (1).jpg";
        //163邮箱用户名就是去掉@163.com
        String username = "wyyuand";
        String password = "yy0829";
        //        Mail.send(smtp,from,to,subject,content,username,password);
        Mail.sendAndCcWithFile(smtp, from, to, subject, content, imgFile, username, password, copyto, filename);

    }
}