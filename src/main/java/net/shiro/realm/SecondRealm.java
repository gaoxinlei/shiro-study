package net.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class SecondRealm extends AuthenticatingRealm{

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
        //用户名
        Object principal = username;
        //数据库中的密码
        Object credentials = getCredentials("xinlei",username);
       
        //所属realm名.
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal , credentials , realmName );
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
        //7验证通过.
        return info;
    }
    
    @Test
    public void test01() {
        SimpleHash result = new SimpleHash("SHA1", "xinlei", "user", 1);
        System.out.println(result);
    }

    private String getCredentials(String source,String key) {
        return new SimpleHash("MD5",source, key,  1).toString();
    }

}
