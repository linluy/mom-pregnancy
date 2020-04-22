package com.wyyuand.service;


import com.wyyuand.dao.AdminMapper;
import com.wyyuand.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    AdminMapper adminDao;

    @Override
    public boolean queryUser(Admin adminLogin) {

        List<Admin> list = adminDao.selectByExample(null);

        boolean success = false;
        String DBloginName;
        String DBpassword;

        for (Admin admin : list) {
           /* System.out.println(admin1.getAdmName());*/
            DBloginName = admin.getAdmName();
            DBpassword = admin.getAdmName();
            if(DBloginName.equals(adminLogin.getAdmName()) && DBpassword.equals(adminLogin.getAdmName())) {
                success = true;
                break;
            }
        }
        return success;
    }
}
