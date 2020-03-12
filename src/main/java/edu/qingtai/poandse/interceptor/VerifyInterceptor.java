package edu.qingtai.poandse.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String pageIndex = request.getParameter("pageIndex");
//        System.out.println(pageIndex);
//        return true;
        response.getWriter().write("null");
        return false;
    }


}
