package net.shiro.service;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;


public interface ShiroService {

    //允许用户访问的方法
    @RequiresRoles(value= {"user"})
    void userMethod();
    
    //允许管理员登陆的方法
    @RequiresRoles(value= {"admin"})
    void adminMethod();
}
