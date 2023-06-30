package com.xk.zhao.community;

import com.xk.zhao.community.dao.DiscussPostMapper;
import com.xk.zhao.community.dao.UserMapper;
import com.xk.zhao.community.entity.DiscussPost;
import com.xk.zhao.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPostList = discussPostMapper.selectDiscussPosts(149,0,10);
        for (DiscussPost dis:discussPostList){
            System.out.println(dis);
        }

        int row = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(row);
    }




    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void insertTest(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("avb");
        user.setEmail("hello@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());

    }

    @Test
    public void updateUser(){

        int row = userMapper.updateStatus(150,1);
        System.out.println(row);
        row = userMapper.updateHeader(150,"http://www.nowcoder.com/102.png");
        System.out.println(row);
        row = userMapper.updatePassword(150,"741852");
        System.out.println(row);
    }
}
