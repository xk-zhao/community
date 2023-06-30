package com.xk.zhao.community.dao;

import com.xk.zhao.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //个人主页-我的帖子
    //通过userId判断 为0就是全部帖子
    //offset 起始行号，limit 每页的帖子
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //有且只有一个参数 在<if>里使用，一定要有别名@Param
    int selectDiscussPostRows(@Param("userId") int userId);

}
