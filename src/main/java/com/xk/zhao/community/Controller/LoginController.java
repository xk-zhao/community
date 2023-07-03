package com.xk.zhao.community.Controller;

import com.google.code.kaptcha.Producer;
import com.xk.zhao.community.entity.User;
import com.xk.zhao.community.services.UserService;
import com.xk.zhao.community.utils.CommunityConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(path="/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/register";
    }

    @RequestMapping(path="/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "/site/login";
    }


    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String,Object> map = userService.register(user);
        System.out.println(map);
        if (map==null||map.isEmpty()){
            System.out.println("success");
            model.addAttribute("msg","注册成功，我们向您发送了一封激活邮件，请尽快激活");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            //System.out.println(1 + map.get("usernameMsg").toString());
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            //System.out.println(2 + map.get("passwordMsg").toString());
            model.addAttribute("emailMsg",map.get("emailMsg"));
            System.out.println(user.getActivationCode());
           // System.out.println(3 + map.get("emailMsg").toString());
            return "/site/register";

        }
    }

    @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activaton(Model model, @PathVariable("userId")int userId,@PathVariable("code") String code){
        int result = userService.activation(userId, code);
        if (result == ACTIVATION_SUCCESS){
            model.addAttribute("msg","激活成功，您的账号已经可以正常登录了");
            model.addAttribute("target","/login");
        }else if (result ==ACTIVATION_REPEAT){
            model.addAttribute("msg","您的账号已经激活过，无须再次激活");
            model.addAttribute("target","/index");
        }else{
            model.addAttribute("msg","激活失败,激活码不正确");
            model.addAttribute("target","/index");
        }
        return "/site/operate-result";
    }

    @Autowired
    private Producer kaptchaProducer;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = "/kaptcha",method = RequestMethod.GET)
    public void getKaptcha(HttpServletResponse response , HttpSession session){
        //生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        //验证码存入session
        session.setAttribute("kaptcha",text);

        //图片输出
        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        }catch (IOException e){
            logger.error("响应验证码失败"+e.getMessage());
        }
    }


    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String username,String password,String code,boolean rememberme,Model model,
                        HttpSession session,HttpServletResponse response){
        String kaptcha = (String) session.getAttribute("kaptcha");
        //任意为空 或者 不相等
        if (StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code) || !kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "/site/login";
        }

        // 验证账号密码
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;

        Map<String ,Object> map = userService.login(username,password,expiredSeconds);
        //成功
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index";
        }else{
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }


    @RequestMapping(path = "logout",method = RequestMethod.GET)
    public String logout(@CookieValue("ticket")String ticket){
        userService.logout(ticket);
        return "redirect:/login";
    }
}
