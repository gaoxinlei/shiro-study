package net.shiro.service.impl;

import org.springframework.stereotype.Service;

import net.shiro.service.ShiroService;
@Service
public class ShiroServiceImpl implements ShiroService{

    @Override
    public void userMethod() {
        System.out.println("user method...");
    }

    @Override
    public void adminMethod() {
        System.out.println("admin method...");
    }

}
