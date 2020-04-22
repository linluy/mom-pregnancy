package com.wyyuand.service;


import com.wyyuand.domain.Admin;
import com.wyyuand.domain.User;

public interface PasswordService {
    public void GetMail(String name,String email,String status);
    public boolean queryEmail(Admin admin);
    public boolean queryUserEmail(User user);

}
