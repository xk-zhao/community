package com.xk.zhao.community.utils;

import com.xk.zhao.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，用于代替session对象
 */
@Component
public class HostHolder {
    //threadlocal做到了线程隔离，通过map做到隔离

    ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public  User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
