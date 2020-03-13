package edu.qingtai.poandse.interceptor;

import edu.qingtai.poandse.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;
//    @Autowired
//    public VerifyInterceptor(final RedisUtils redisUtils){
//        this.redisUtils = redisUtils;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String rd3session = request.getHeader("sessionid");

        if(rd3session != null){
            if(redisUtils.get(rd3session) == null){
                response.getWriter().write("login");
                return false;
            }else{
                return true;
            }
        }else{
            response.getWriter().write("no sessionid");
            return false;
        }
    }
}
