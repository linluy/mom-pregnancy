package com.wyyuand.utils;

import com.wyyuand.domain.User;
import com.wyyuand.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//         http://127.0.0.1:8080/file/uploadimg.jsp
//       http://127.0.0.1:8080/manage/uploadImage/convertStringtoImage

//http://192.168.172.1:8080/manage/file/uploadimg.jsp
@Controller
@RequestMapping("/uploadImage")
public class UploadImage {

    @Autowired
    static UserService userService;

    static String SHORT_PATH = "d:\\upload\\user\\";
    //       http://127.0.0.1:8080/fileupload/uploadimg.jsp
    @RequestMapping("/convertStringtoImage")
    public static void convertStringtoImage(String encodedImageStr, String fileName) {
        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);
            //
            FileOutputStream imageOutFile = new FileOutputStream(SHORT_PATH + fileName + ".jpg");
            imageOutFile.write(imageByteArray);
            imageOutFile.close();
            System.out.println("Image Successfully Stored");
            //此处修改数据库中图片的位置

        } catch (FileNotFoundException fnfe) {
            System.out.println("Image Path not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Exception while converting the Image " + ioe);
        }
    }

}

