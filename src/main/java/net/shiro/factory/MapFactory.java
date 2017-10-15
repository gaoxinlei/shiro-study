package net.shiro.factory;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.shiro.bean.Auth;
import net.shiro.mapper.AuthMapper;

public class MapFactory {

    @Autowired
    private AuthMapper authMapper;
    
    public LinkedHashMap<String, String> getAuthMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //查询数据库得到每一条权限记录封入.
        /*
         * /login.jsp = anon
            /logo/* = anon
            /img/* = anon
            /js/* = anon
            /css/* = anon
            /*.jar = anon
            /login = anon
            /shiro/logout = logout
            /user.jsp = roles[user]
            /admin.jsp = roles[admin]
            /** = authc
         */
        /*map.put("/login.jsp", "anon");
        map.put("/shiro/logout","logout");
        map.put("/**","authc");*/
        
        //从数据库查询权限.
        List<Auth> authList = authMapper.selectAll();
        for(Auth auth:authList) {
            map.put(auth.getUrl(),auth.getDesc());
        }
        return map;
    }
}
