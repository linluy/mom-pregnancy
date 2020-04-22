package com.wyyuand;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class Mail {

    private MimeMessage mimeMessage;
    //Mime邮件对象
    private Session session;
    //邮件会话对象
    private Properties properties;
    //系统属性
    private boolean needAuth = false;
    //smtp是否需要认证
    //smtp认证的用户名和密码
    private String username;
    private String password;
    private Multipart multipart;    //Multipart对象 邮件内容 标题 附件等内容添加到这里面 然后生成MimeMessage对象
    /**
     * 构造方法
     * @param smtp
     */
    public Mail(String smtp){
         setSmtpHost(smtp);
         createMimeMessage();
     }
    /**
     * 创建MimeMessage邮件对象
     * @return
     */
    public boolean createMimeMessage() {
        //获取邮件会话对象 session = Session.getDefaultInstance(properties, null);
        //创建Mime邮件对象
        mimeMessage = new MimeMessage(session);
        multipart = new MimeMultipart();
        return true;
    }
    /**
     *  设置邮件发送服务器
     * @param hostName
     */
    public void setSmtpHost(String hostName) {
        if (properties==null){
            properties=System.getProperties();
            //获得系统属性对象
        } properties.put("mail.smtp.host",hostName);
        //设置smtp主机
    }
    /**
     * 设置smtp是否需要认证
     * @param need
     */
    public void setNeedAuth(boolean need){
        if (properties == null){
            properties = System.getProperties();
        }
        if (need){
            properties.put("mail.smtp.auth","true");
        }else {
            properties.put("mail.smtp.auth","false");
        }
    }
    /**
     * 7 发件人的用户名和密码 163的用户名就是邮箱的前缀
     * @param username
     * @param password
     */
    public void setNamePassword(String username,String password){
        this.username = username;
        this.password = password;
    }
    /**
     * 1 邮件主题
     * @param subject
     * @return
     */
    // String subject = "重置密码";
    public boolean setSubject(String subject){
        try {
            mimeMessage.setSubject(subject);
            return true;
        } catch (MessagingException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 2 邮件正文
     * @param mailBody
     * @return
     */
    public boolean setBody(String mailBody){
        BodyPart bodyPart = new MimeBodyPart();
        try {
            bodyPart.setContent(""+mailBody,"text/html;charset=utf-8");
            multipart.addBodyPart(bodyPart);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 2 邮件正文（带图片的）
     * @param mailBody
     * @param imgFile
     * @return
     */
    public boolean setBodyWithImg(String mailBody,String imgFile){
        BodyPart content = new MimeBodyPart();
        BodyPart img = new MimeBodyPart();
        try {
            multipart.addBodyPart(content);
            multipart.addBodyPart(img);
            ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(new FileInputStream(imgFile),"application/octet-stream");
            //DataHandler imgDataHandler = new DataHandler(new FileDataSource(imgFile));
            DataHandler imgDataHandler = new DataHandler(byteArrayDataSource);
            img.setDataHandler(imgDataHandler);
            String imgFilename = imgFile.substring(imgFile.lastIndexOf("/")+1);
            //图片文件名 //注意：Content-ID的属性值一定要加上<>，不能直接写文件名
             String headerValue = "<"+imgFilename+">";
            img.setHeader("Content-ID",headerValue);
            //为图片设置文件名，有的邮箱会把html内嵌的图片也当成附件
            img.setFileName(imgFilename);
            //在html代码中要想显示刚才的图片名 src里不能直接写Content-ID的值，要用cid:这种方式
//            mailBody+="<a href='http://127.0.0.1:8080/manage/login_register/item_forgetPassword2.jsp'>点击链接</a>";
            content.setContent(""+mailBody,"text/html;charset=utf-8");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } }
    /**
     * 3 邮件添加附件
     * @param file
     * @return
     */
    public boolean addFileAffix(String file){
        String[] fileArray = file.split(",");
        for (int i = 0; i < fileArray.length; i++) {
            FileDataSource fileDataSource = new FileDataSource(fileArray[i]);
            try { BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setDataHandler(new DataHandler(fileDataSource));
                bodyPart.setFileName(fileDataSource.getName());
                multipart.addBodyPart(bodyPart);
            } catch (MessagingException e) {
                e.printStackTrace(); return false;
            } } return true; }
    /**
     * 4 发件人邮箱
     * @param from
     * @return
     */
    public boolean setFrom(String from){
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     *5   收件人邮箱
     * @param to
     * @return
     */

    public boolean setTo(String to){
        if (to==null)
            return false;
        try {
            //电子邮件可以有三种类型的收件人，分别to、cc（carbon copy）和bcc（blind carbon copy），分别是收件人、抄送、密送
            mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 6 抄送人邮箱 字符串中逗号分开
     * @param copyto
     * @return
     */
    public boolean setCopyTo(String copyto){
        if (copyto == null)
            return false;
        try {
            mimeMessage.setRecipients(Message.RecipientType.CC,(Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 发送
     * @param copyto
     * @return
     */ public boolean sendOut(String copyto){
        try {
            //multipart放入message
            mimeMessage.setContent(multipart);
            mimeMessage.saveChanges();
            Session mailSession = Session.getInstance(properties, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(properties.getProperty("mail.smtp.host"),username,password);
            transport.sendMessage(mimeMessage,mimeMessage.getRecipients(Message.RecipientType.TO));

            if (copyto!=null){
              //  transport.sendMessage(mimeMessage,mimeMessage.getRecipients(Message.RecipientType.CC));
            }
            System.out.println("邮件发送成功");
            transport.close();
            return true;
        } catch (MessagingException e) {
            System.out.println("邮件发送失败");
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 该方法调用上边定义的方法 选择性的组合 完成邮件发送
     * 普普通通的一对一发送
     * @param smtp
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param username
     * @param password
     * @return
     */
    public static boolean send(String smtp,String from,String to,String subject, String content,String username,String password){
        Mail mail = new Mail(smtp);
        mail.setNeedAuth(true);        //需要认证
        if (!mail.setSubject(subject))
        return false;
        if (!mail.setBody(content))
            return false;
        if (!mail.setFrom(from))
            return false;
        if (!mail.setTo(to)){
            return false;
        }
        mail.setNamePassword(username,password);
        if (!mail.sendOut(null))
            return false;
        return true;
    }
    /**
     * 带附件的正文有图片的带有抄送的邮件
     * @return
     */
    public static boolean sendAndCcWithFile(String smtp,String from,String to,String subject, String content,String imageFile,String username,String password,String copyto,String filename){
        Mail mail = new Mail(smtp);
        mail.setNeedAuth(true);
        //需要认证
        if (!mail.setSubject(subject)) {               //1 设置主题
            return false;
        } if (!mail.setBodyWithImg(content,imageFile)) {      //2 设置带图片的邮件正文
            return false;
        } if (!mail.addFileAffix(filename))             //3 邮件添加附件
            return false;
        if (!mail.setFrom(from)) {                      //4 发件人
            return false;
        } if (!mail.setTo(to)){                     //5 收件人
            return false;
        } if (!mail.setCopyTo(copyto)) {            //6 抄送人
            return false;
        }
        mail.setNamePassword(username,password);               //7 发件人的用户名和密码 163的用户名就是邮箱的前缀
        if (!mail.sendOut(copyto))
            return false;

        return true;
    }
}
