package net.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class ShiroRealm extends AuthorizingRealm{
//认证，实现AuthenticationgRealm ,认证和授权，继承 AuthorizingRealm 并实现两个抽象方法doGetAuthenticationInfo
    //和doGetAuthorizationInfo
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("SHA1 "+token.hashCode());
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
        Object credentials = null;
        if("user".equals(username)) {
            credentials = "5cc32e366c87c4cb49e4309b75f57d64";
        }
        if("gao".equals(username)) {
            credentials = "682deed3108576d38b8ba6bb64204658";
        }
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
        //SimpleHash 的构造函数，第一个参数为算法，第二个为源，即我的密码，第三个为盐，
        //即用来加密的盐值，这里使用的用户名，最后一个为加密次数。
        SimpleHash result = new SimpleHash("MD5", "xinlei", "gao", 1);
        System.out.println(result);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //打印标记信息
        System.out.println("doGetAuthorizationInfo:"+principals.toString());
        //1.获得授权主体
        Object principal = principals.getPrimaryPrincipal();
        //2.准备一个set存放roles
        Set<String> roles = new HashSet<>();
        //3.存放固有角色
        roles.add("user");
        if("gao".equals(principal)) {
            //存放限权角色。角色信息均可以来自数据库
            roles.add("admin");
            //亦可用
            //((SimpleAuthorizationInfo)info).addRole("admin");
            //只能创建接口的实现类。AuthorizationInfo接口本身没有addRole方法。
            
        }
        //创建返回的授权信息
        AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //亦可先建立info，依次添加。
        return info;
    }
    
    


}
