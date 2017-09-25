package net.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AuthController {

    @RequestMapping("/toTarget")
    public String toTarget() {
        return "target";
    }
    
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
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
}
