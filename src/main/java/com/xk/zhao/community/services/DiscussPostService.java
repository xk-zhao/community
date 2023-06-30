package com.xk.zhao.community.services;

import com.xk.zhao.community.dao.DiscussPostMapper;
import com.xk.zhao.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子Service
 */
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * 帖子返回
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    /**
     * 返回行数
     * @param userId
     * @return
     */
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
