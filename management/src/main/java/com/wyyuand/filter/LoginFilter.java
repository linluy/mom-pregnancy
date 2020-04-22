package com.wyyuand.filter;



import com.wyyuand.utils.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        boolean isLogin = false;      //校验是否有登陆状态
        Object loginFlag  = session.getAttribute(Constants.SEESION_LOGIN_FLAG);      // !!!!!!!!
        if(loginFlag != null ){
            isLogin=true;
        }
        String uri = request.getRequestURI();

        if(isLogin || isPassUrl(uri) || isPassExt(uri)){                      //如果有登陆状态，放行
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }


    private boolean isPassExt(String uri) {
        int start = uri.lastIndexOf(".")+1;
        String ext = uri.substring(start,uri.length());

        ext = ext.toLowerCase();
        if("js".equals(ext) ||"css".equals(ext)
                || "eot".equals(ext) ||"svg".equals(ext)
                ||"ttf".equals(ext) ||"woff".equals(ext) ||"woff2".equals(ext) || "png".equals(ext)
                || "jpg".equals(ext) ||"gif".equals(ext) || "bmp".equals(ext)
        ){
            return true;
        }
        return  false;
    }

    private boolean isPassUrl(String uri) {
        return  uri.contains("login") || uri.contains("vc");
    }

    @Override
    public void destroy() {

    }
}
