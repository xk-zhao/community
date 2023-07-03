package com.xk.zhao.community.Controller.interceptor;


import com.xk.zhao.community.entity.LoginTicket;
import com.xk.zhao.community.entity.User;
import com.xk.zhao.community.services.UserService;
import com.xk.zhao.community.utils.CookieUtil;
import com.xk.zhao.community.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //取出Cookie中的凭证
        String ticket = CookieUtil.getValue(request,"ticket");
        if (ticket!=null){
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            //检查凭证是否有效
            /**
             * 不为空，状态登录，不过期 则有效
             */
            if(loginTicket !=null && loginTicket.getStatus()==0 && loginTicket.getExpired().after(new Date())){
                //根据凭证找到用户
                User user = userService.findUserById(loginTicket.getUserId());
                //在本次请求中拥有用户
                //多线程中隔离存储对象
                hostHolder.setUser(user);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user!=null && modelAndView!=null){
            modelAndView.addObject("loginUser",user);
        }

        return ;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
