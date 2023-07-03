package com.xk.zhao.community;

import com.xk.zhao.community.services.UserService;
import com.xk.zhao.community.utils.MailClient;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class mailtest {
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","xk");

        String content = templateEngine.process("/mail/demo",context);
        System.out.println(content);
        mailClient.sendMail("xk.zhao@outlook.com","test","welcome");

    }

    @Test
    public void testTextMail(){
        mailClient.sendMail("xk.zhao@outlook.com","test","welcome");
    }
}
