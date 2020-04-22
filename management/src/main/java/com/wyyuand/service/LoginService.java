package com.wyyuand.service;

import com.wyyuand.domain.Admin;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    public boolean queryUser(Admin admin);
}
