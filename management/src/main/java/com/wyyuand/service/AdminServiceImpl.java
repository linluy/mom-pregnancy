package com.wyyuand.service;

import com.wyyuand.dao.AdminMapper;
import com.wyyuand.dao.AdminMapperExt;
import com.wyyuand.domain.Admin;
import com.wyyuand.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 具体逻辑
 */
@Service
public class AdminServiceImpl implements AdminService, Serializable {


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminMapperExt adminMapperExt;

    @Override
    public Admin queryById(String id) throws Exception {

        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public List<Admin> queryAll() throws Exception {
       List<Admin> list = adminMapper.selectByExample(null);   //从数据库查询
       /* for (Admin admin : list) {
            System.out.println(admin.getAdmName());
        }*/
        return list;
    }

    @Override
    public boolean save(Admin admin) throws Exception {
        admin.setAdmId(StringUtil.pid());
        admin.setAdmStatus("0");
        int count = adminMapper.insert(admin);
        System.out.println(count);
        return count>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(Admin admin) throws Exception {

        admin.setAdmCreateTime(new Date());
        int count = adminMapper.updateByPrimaryKey(admin);
        return  count >0;


    }
    @Override
//    public boolean updatePassword(String admPassword,String admEmail) throws Exception {
    public boolean updatePassword(Admin admin) throws Exception {

//        System.out.println(admPassword+" "+admEmail);
        admin.setAdmCreateTime(new Date());
        int count = adminMapperExt.updateByEmail(admin);
        return count>0;
    }

    @Override
    public Admin queryByEmail(String admEmail, String password) {

        Admin admin = adminMapperExt.selectAdmin(admEmail, password);
        return admin;
    }


}
