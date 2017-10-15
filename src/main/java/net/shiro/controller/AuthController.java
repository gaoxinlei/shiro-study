package net.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.shiro.bean.User;
import net.shiro.service.ShiroService;
@Controller
public class AuthController {

    @Autowired
    private ShiroService shiroService;
    //基础环境搭建完成测试
    @RequestMapping("/toTarget")
    public String toTarget() {
        return "target";
    }
    //登陆,权限,授权.
    @RequestMapping("/login")
    public String login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()) {
            System.out.println(token.hashCode());
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
    //指定某个方法需要某个权限
    @RequestMapping("/qualifiedMethod")
    public String qualifiedMethod() {
        shiroService.userMethod();
        shiroService.adminMethod();
        return "redirect:/list.jsp";
    }
    
}
