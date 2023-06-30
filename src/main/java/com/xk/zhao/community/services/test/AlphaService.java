package com.xk.zhao.community.services.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class  AlphaService {
    public AlphaService(){
        System.out.println("construct");
    }
    //初始化方法，在构造器类之后调用
//    @PostConstruct
//    public void init(){
//        System.out.println("init alphaservice");
//    }
//    @PreDestroy
//    public void destroy(){
//        System.out.println("destory");
//    }
}
