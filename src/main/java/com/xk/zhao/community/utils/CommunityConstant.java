package com.xk.zhao.community.utils;

public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;
    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;
    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;
    /**
     * 默认状态的登陆凭证的超时时间 s
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 勾上 记住我 之后的超时时间 s
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 *100;

}
