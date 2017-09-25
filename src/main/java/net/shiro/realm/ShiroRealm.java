package net.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.aspectj.util.LangUtil.ProcessController.Thrown;

public class ShiroRealm extends AuthenticatingRealm{

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println(token.hashCode());
        //1.强转
        UsernamePasswordToken up = (UsernamePasswordToken) token;
        //2.获得提交的用户名.
        String username = up.getUsername();
        System.out.println("提交的用户名:"+username);
        //3.查找数据库.
        //4.未知用户名.
        if("unknown".equals(username)) {
            throw new UnknownAccountException("unknown username");
        }
        //5.限定用户名.
        if("qualified".equals(username)) {
            throw new LockedAccountException("locked username");
        }
        //6.密码错误.
        Object principal = username;
        Object credentials = "xinlei";
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal , credentials , realmName );
        //7验证通过.
        return info;
    }


}
