package com.yumita.shiro.realm;

import cn.hutool.jwt.JWT;
import com.yumita.entity.FileupdownUser;
import com.yumita.service.FileupdownUserService;
import com.yumita.token.JwtToken;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdownUserRealm extends AuthorizingRealm {
    @Autowired
    private FileupdownUserService fileupdownUserService;

    // 扶着对应数据源的授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 负责对应数据源的认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取jwt字符串
        String jwtToken = (String) authenticationToken.getPrincipal();
        // 破解jwt字符串为JWT对象
        JWT jwt = JWT.of(jwtToken);
        // 从JWT对象中获得username
        String username = (String) jwt.getPayload("username");
        // 通过用户JWT对象传来的username来从数据库中查找出正确的数据
        FileupdownUser dbUser = this.fileupdownUserService.getUserByUsername(username);
        // 如果查找出的对象为空则直接返回null
        if (dbUser == null) {
            return null;
        }
        // 如果不为空，则返回SimpleAuthenticationInfo对象，该对象会交给shiro的密码适配器进行比对
        return new SimpleAuthenticationInfo(username, dbUser.getUserPassword(), this.getName());
    }
}
