package com.yumita.shiro.matcher;

import com.yumita.entity.FileupdownUser;
import com.yumita.service.FileupdownUserService;
import com.yumita.token.JwtToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
*
* */
@Component
public class UserCredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private FileupdownUserService fileupdownUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 获取jwt
        JwtToken jwtToken = (JwtToken) token;
        // 判断jwtToken中的密码是否为空，如果为空则不需要进行密码匹配，直接返回true
        if (jwtToken.getPassword() == null) {
            return true;
        }
        // 获取token带有的用户输入的密码
        String inPassword = String.valueOf(jwtToken.getPassword());
        // info为从realm中传过来的SimpleAuthenticationInfo对象，通过其获取数据库中的username
        String dbUsername = String.valueOf(info.getPrincipals());
        // 通过数据库中的用户名查找数据库，得出用户对象
        FileupdownUser dbUser = fileupdownUserService.getUserByUsername(dbUsername);
        // 从用户对象得出数据库中对应的密码
        String dbPassword = dbUser.getUserPassword();
        // 将用户输入密码和数据库中的密码进行比对
        return this.equals(inPassword, dbPassword);
    }
}
