package net.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

public class ShiroRealm implements Realm{

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // TODO Auto-generated method stub
        return null;
    }

}
